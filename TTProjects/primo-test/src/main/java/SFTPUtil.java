

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;
import com.jcraft.jsch.ChannelSftp.LsEntry;

public class SFTPUtil {

	public static boolean debug = true;
	private static final int DATA_TRANSFER_TIME_OUT = 1800000;//1,800,000 = 30 min
	private static final int CONNECTION_TIME_OUT = 1800000;//1,800,000 = 30 min

	/**
	 * 0 - host name (il-priom17)
	 * 1 - username  (primo)
	 * 2 - password  (primo3)
	 * 3 - target folder  (/tmp/barak) - sftp server
	 * 4 - source folder  ("C:\\Documents and Settings\\barakh\\Desktop\\timm")
	 * @param args
	 * @throws JSchException
	 * @throws SftpException
	 */
	public static void main(String[] args)  {

		try {
			ChannelSftp channel = SFTPUtil.connect(args[0], args[1], args[2]);
			SFTPUtil.copyFilesFromSFTP(channel, args[3], args[4]);
			SFTPUtil.close(channel);
			System.out.println("Finished");
		} catch (JSchException | SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static void info(String message){
		if (debug) {
			System.out.println(SFTPUtil.class + ":" + message);
		}
	}

	private static void warn(Exception e){
		warn("",e);
	}

	private static void warn(String message, Exception e){
		System.err.println(SFTPUtil.class + ":" + message);
		e.printStackTrace();
		
	}
	

	public static ChannelSftp connect(String server, Integer port, String username, final String password)
										throws JSchException {
		ChannelSftp channel = null;

		try {
			info("Server:["+server+"],Port:["+port+"],Username:["+username+"],Password:["+password+"]");
			JSch jsch = new JSch();
			Session session = jsch.getSession(username, server, port);
			session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
			session.setPassword(password);
			session.setUserInfo(new MyUserInfo(password));
			session.setTimeout(CONNECTION_TIME_OUT);
			session.setServerAliveInterval(DATA_TRANSFER_TIME_OUT);
			session.connect();

			channel = (ChannelSftp)session.openChannel("sftp");
			channel.connect();
		} catch (JSchException e) {
			close(channel);
			throw e;
		}

		return channel;
	}
	
	
	public static ChannelSftp connect(String server, String username, final String password)
					throws JSchException {
		ChannelSftp channel = null;

		info("Server:["+server+"],Username:["+username+"],Password:["+password+"]");
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(username, server, 22);
			session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
			session.setPassword(password);
			session.setUserInfo(new MyUserInfo(password));
			session.setTimeout(CONNECTION_TIME_OUT);
			session.setServerAliveInterval(DATA_TRANSFER_TIME_OUT);
			session.connect();

			channel = (ChannelSftp)session.openChannel("sftp");
			channel.connect();
		} catch (JSchException e) {
			close(channel);
			throw e;
		}

		return channel;
	}

	public static FilesCopiedResult copyFilesFromSFTP(ChannelSftp channel, String sftpDir,
			 							 String localDir)  throws SftpException {
		return copyFilesFromSFTP(channel, sftpDir, localDir,  addYears(new Date(), -100), new Date(), null, false);
	}
	
    /**
     * Add the given amount of years to the given date. It actually converts the date to Calendar
     * and calls {@link CalendarUtil#addYears(Calendar, int)} and then converts back to date.
     * @param date The date to add the given amount of years to.
     * @param years The amount of years to be added to the given date. Negative values are also
     * allowed, it will just go back in time.
     */
    public static Date addYears(Date date, int years) {
        Calendar calendar = toCalendar(date);
        addYears(calendar, years);
        return calendar.getTime();
    }
    /**
     * Convert the given date to a Calendar object. The TimeZone will be derived from the local
     * operating system's timezone.
     * @param date The date to be converted to Calendar.
     * @return The Calendar object set to the given date and using the local timezone.
     */
    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        return calendar;
    }
    /**
     * Add the given amount of years to the given calendar. The changes are reflected in the given
     * calendar.
     * @param calendar The calendar to add the given amount of years to.
     * @param years The amount of years to be added to the given calendar. Negative values are also
     * allowed, it will just go back in time.
     */
    public static void addYears(Calendar calendar, int years) {
        calendar.add(Calendar.YEAR, years);
    }
    
	@SuppressWarnings("unchecked")
	public static FilesCopiedResult copyFilesFromSFTP(ChannelSftp channel, String sftpDir,
			 							 String localDir,
			 							 Date minDate, Date maxDate,
			 							 Collection<String> fileRegexpFormats,
									 	 boolean deleteFiles)
									throws SftpException {

		channel.cd(sftpDir); //change direcotry to the specified source directory.

		int counter = 0;
		Date maxFileDate = minDate;

		info("Raw file list: "+toString(channel.ls("*"), ","));

		for (LsEntry e : (Vector<LsEntry>)channel.ls("*")) {
			if (!validFile(e, minDate, maxDate, fileRegexpFormats)) {
				continue;
			}

			copyFileFromSFTP(channel, e, localDir, deleteFiles);
			counter++;
			Date fileDate = getFileDate(e);
			maxFileDate = Max(fileDate, maxFileDate);
		}

		return new FilesCopiedResult(counter, maxFileDate);
	}
	public static String toString(Collection arr, String delimiter) {
		return toString(arr.toArray(new Object[] {}), delimiter);
	}
	public static String toString(Object[] arr, String delimiter) {
		return toString(arr, "", delimiter);
	}

	public static String toString(Object[] arr, String prefix,
			String delimiter) {

		StringBuilder sb = new StringBuilder();

		for (Object s : arr) {
			sb.append(prefix).append(s.toString()).append(delimiter);
		}

		return removeLast(sb, delimiter).toString();
	}
	public static StringBuilder removeLast(StringBuilder str, String remove) {

		int index = str.lastIndexOf(remove);

		if (index > -1) {
			str = str.delete(index, str.length());
		}

		return str;
	}
	public static String toString(Object obj) {
		if (obj == null) {
			return null;
		}

		return obj.toString();
	}
    public static Date Max(Date d1, Date d2) {
    	return d1.after(d2)? d1 : d2 ;
    }
	public static int copyFilesToSFTP(ChannelSftp channel, String sourceDir, String sftpDir)
				throws IOException, SftpException {
		File sourceDirectory = new File(sourceDir);
		int numCopied = 0;

		if (!sourceDirectory.exists()) {
			throw  new FileNotFoundException("Can not find directory: " + sourceDirectory);
		}
		for (File f : sourceDirectory.listFiles()) {
			if (f.isFile()) {
				copyFileToSFTP(channel, f, sftpDir);
				numCopied++;
			}
		}

		return numCopied;
	}

	public static void copyFileToSFTP(ChannelSftp channel, File file, String ftpDir)
		throws IOException, SftpException {

		FileInputStream fis = new FileInputStream(file);
		channel.put(fis, ftpDir);
		fis.close();
	}

	public static void copyFileToSFTP(ChannelSftp channel, File file, String ftpDir, String ftpFileName)
			throws IOException, SftpException {

			FileInputStream fis = new FileInputStream(file);
			channel.put(fis, ftpDir + "/" + ftpFileName);
			fis.close();
	}
	
	public static void close(ChannelSftp channel) {
		try{channel.getSession().disconnect();} catch (Exception e){warn(e);}
		try{channel.disconnect();} catch (Exception e){warn(e);}
	}

	private static Date getFileDate(LsEntry e) {
		return new Date((long)e.getAttrs().getMTime()*1000);
	}

	private static void copyFileFromSFTP(ChannelSftp channel, LsEntry e,
										String destDir, boolean deleteFile) {
		InputStream in = null;
		OutputStream out = null;

		boolean successInCopy = true;
		String destFilePath = null;

		try{
			String fileName = e.getFilename();
			in = channel.get(fileName);

			destFilePath = destDir + "/" + fileName;

			out = new FileOutputStream(destFilePath);
			info("Coping file " + destFilePath);

			copy(in, out);

			if (deleteFile) {
				info("About to delete "+fileName);
				channel.rm(fileName);
			}


		} catch(Exception ex){
			successInCopy = false;
			throw new RuntimeException("Failed while trying to copy " +
					"files from sftp server. " +
					"exception message is: ",ex);
		} finally{
			if (in != null){
				try{in.close();} catch (Exception ex){warn(ex);}
			}
			if (out != null){
				try{out.close();} catch (Exception ex){warn(ex);}
			}

			if (successInCopy) {
				File f = new File(destFilePath);
				f.setLastModified(getFileDate(e).getTime());
			}
		}
	}
	  public static final String UTF8_ENCODING = "UTF-8";
	  private static final int DEFAULT_BUFFER_SIZE = 1024*4;
	  /**
	   * Copy bytes from an <code>InputStream</code> to an <code>OutputStream</code>.
	   */
	  public static void copy (final InputStream input, final OutputStream output) throws IOException {
	    copy(input, output, DEFAULT_BUFFER_SIZE);
	  }

	  /**
	   * Copy bytes from an <code>InputStream</code> to an <code>OutputStream</code>.
	   */
	  public static void copy (final long contentLength, final InputStream input,
	      final OutputStream output) throws IOException {
	    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
	    int n = 0;
	    long remaining = contentLength;
	    int lengthToRead = 0;
	    while (remaining > 0) {
	      lengthToRead = remaining > DEFAULT_BUFFER_SIZE ? DEFAULT_BUFFER_SIZE : (int)remaining;
	      n = input.read(buffer, 0, lengthToRead);
	      if (n == -1) {
	        break;
	      }
	      remaining -= n;
	      output.write(buffer, 0, n);
	    }
	  }
	  /**
	   * Copy bytes from an <code>InputStream</code> to an <code>OutputStream</code>.
	   * @param bufferSize Size of internal buffer to use.
	   */
	  public static void copy (final InputStream input, final OutputStream output,
	      final int bufferSize) throws IOException {
	    byte[] buffer = new byte[bufferSize];
	    int n = 0;
	    while (-1 != (n = input.read(buffer))) {
	      output.write(buffer, 0, n);
	    }
	  }
	  
	  
	private static boolean validFile(LsEntry e, Date minDate, Date maxDate,
									 Collection<String> fileRegexpFormats) {

		boolean matchedName = true;
		info("File: ["+e.getFilename()+"]      File date: ["+getFileDate(e)+"]      Start: ["+minDate+"]         End: ["+maxDate+"]");

		if (e.getAttrs().isDir()){ //make sure its a file.
			return false;
		}

		if (fileRegexpFormats != null) {
			matchedName = false;
			for (String regExp : fileRegexpFormats) {
				if(e.getFilename().toLowerCase().matches(regExp)) {
					matchedName = true;
					break;
				}
			}
		}

		if (!matchedName) {
			return false;
		}

		Date fileDate = getFileDate(e);

		if (minDate != null && (fileDate.before(minDate) || fileDate.equals(minDate))) {
			return false;
		}
		if (maxDate != null && fileDate.after(maxDate)) {
			return false;
		}

		return true;
	}

	public static class MyUserInfo implements UserInfo, UIKeyboardInteractive {


		String passwd;

		public MyUserInfo(String passwd) {
			super();
			this.passwd = passwd;
		}

		public String getPassword(){ return passwd; }

		public boolean promptYesNo(String str){
			return true;
		}

		public String getPassphrase(){
			return null;
		}

		public boolean promptPassphrase(String message){
			return false;
		}

		public boolean promptPassword(String message){
			return false;
		}

		public void showMessage(String message){
			//System.out.println(message);
		}

		public String[] promptKeyboardInteractive(String arg0, String arg1, String arg2, String[] arg3, boolean[] arg4) {
			return new String[] {passwd};
		}
	}

	/**
	 * @param channel
	 * @param fromSFtpDir
	 * @param fromSFtpFile
	 * @param toSFtpDir if null then same directory
	 * @param toSFtpFile if null then same name
	 * @throws SftpException
	 */
	public static void renameSFTPFile(ChannelSftp channel, String fromSFtpDir, String fromSFtpFile, String toSFtpDir, String toSFtpFile) throws SftpException {
		String from = fromSFtpDir + "/" + fromSFtpFile;
		String to = (toSFtpDir == null) ? fromSFtpDir : toSFtpDir;
		to += "/";
		to += (toSFtpFile == null) ? fromSFtpFile : toSFtpFile;
		channel.rename(from, to);
	}
	
	public static void deleteSFTPFile(ChannelSftp channel, String sftpDir, String sftpFile) throws SftpException {
		channel.rm(sftpDir + "/" + sftpFile);
	}
}
