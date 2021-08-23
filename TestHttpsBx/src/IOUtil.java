

/**
 * Title:
 * Description:
 * Company:  Exlibris
 * @author zeevs
 * @version 1.0
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;


/**
 * put your documentation comment here
 */
public final class IOUtil {
  public static final String UTF8_ENCODING = "UTF-8";
  private static final int DEFAULT_BUFFER_SIZE = 1024*4;

  /**
   * Private constructor to prevent instantiation.
   */
  private IOUtil () {
  }

  /**
   * Unconditionally close an <code>OutputStream</code>.
   * Equivalent to {@link OutputStream#close()}, except any exceptions will be ignored.
   * @param output A (possibly null) OutputStream
   */
  public static void shutdownStream (final OutputStream output) {
    if (null == output) {
      return;
    }
    try {
      output.close();
    } catch (final IOException ioe) {}
  }

  /**
   * Unconditionally close an <code>InputStream</code>.
   * Equivalent to {@link InputStream#close()}, except any exceptions will be ignored.
   * @param input A (possibly null) InputStream
   */
  public static void shutdownStream (final InputStream input) {
    if (null == input) {
      return;
    }
    try {
      input.close();
    } catch (final IOException ioe) {}
  }

  ///////////////////////////////////////////////////////////////
  // Core copy methods
  ///////////////////////////////////////////////////////////////
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

  /**
   * Copy chars from a <code>Reader</code> to a <code>Writer</code>.
   */
  public static void copy (final Reader input, final Writer output) throws IOException {
    copy(input, output, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Copy chars from a <code>Reader</code> to a <code>Writer</code>.
   * @param bufferSize Size of internal buffer to use.
   */
  public static void copy (final Reader input, final Writer output, final int bufferSize) throws IOException {
    char[] buffer = new char[bufferSize];
    int n = 0;
    while (-1 != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
    }
  }

  ///////////////////////////////////////////////////////////////
  // Derived copy methods
  // InputStream -> *
  ///////////////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////
  // InputStream -> Writer
  /**
   * Copy and convert bytes from an <code>InputStream</code> to chars on a
   * <code>Writer</code>.
   * The platform's default encoding is used for the byte-to-char conversion.
   */
  public static void copy (final InputStream input, final Writer output) throws IOException {
    copy(input, output, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Copy and convert bytes from an <code>InputStream</code> to chars on a
   * <code>Writer</code>.
   * The platform's default encoding is used for the byte-to-char conversion.
   * @param bufferSize Size of internal buffer to use.
   */
  public static void copy (final InputStream input, final Writer output, final int bufferSize) throws IOException {
    final InputStreamReader in = new InputStreamReader(input);
    copy(in, output, bufferSize);
  }

  /**
   * Copy and convert bytes from an <code>InputStream</code> to chars on a
   * <code>Writer</code>, using the specified encoding.
   * @param encoding The name of a supported character encoding. See the
   * <a href="http://www.iana.org/assignments/character-sets">IANA
   * Charset Registry</a> for a list of valid encoding types.
   */
  public static void copy (final InputStream input, final Writer output, final String encoding) throws IOException {
    InputStreamReader in = new InputStreamReader(input, encoding);
    copy(in, output);
  }

  /**
   * Copy and convert bytes from an <code>InputStream</code> to chars on a
   * <code>Writer</code>, using the specified encoding.
   * @param encoding The name of a supported character encoding. See the
   *        <a href="http://www.iana.org/assignments/character-sets">IANA
   *        Charset Registry</a> for a list of valid encoding types.
   * @param bufferSize Size of internal buffer to use.
   */
  public static void copy (final InputStream input, final Writer output, final String encoding,
      final int bufferSize) throws IOException {
    InputStreamReader in = new InputStreamReader(input, encoding);
    copy(in, output, bufferSize);
  }

  ///////////////////////////////////////////////////////////////
  // InputStream -> String
  /**
   * Get the contents of an <code>InputStream</code> as a String.
   * The platform's default encoding is used for the byte-to-char conversion.
   */
  public static String toString (final InputStream input) throws IOException {
    return  toString(input, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Get the contents of an <code>InputStream</code> as a String.
   * The platform's default encoding is used for the byte-to-char conversion.
   * @param bufferSize Size of internal buffer to use.
   */
  public static String toString (final InputStream input, final int bufferSize) throws IOException {
    StringWriter sw = new StringWriter();
    copy(input, sw, bufferSize);
    return  sw.toString();
  }

  /**
   * Get the contents of an <code>InputStream</code> as a String.
   * @param encoding The name of a supported character encoding. See the
   *    <a href="http://www.iana.org/assignments/character-sets">IANA
   *    Charset Registry</a> for a list of valid encoding types.
   */
  public static String toString (final InputStream input, final String encoding) throws IOException {
    return  toString(input, encoding, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Get the contents of an <code>InputStream</code> as a String.
   * @param encoding The name of a supported character encoding. See the
   *   <a href="http://www.iana.org/assignments/character-sets">IANA
   *   Charset Registry</a> for a list of valid encoding types.
   * @param bufferSize Size of internal buffer to use.
   */
  public static String toString (final InputStream input, final String encoding,
      final int bufferSize) throws IOException {
    StringWriter sw = new StringWriter();
    copy(input, sw, encoding, bufferSize);
    return  sw.toString();
  }

  ///////////////////////////////////////////////////////////////
  // InputStream -> byte[]
  /**
   * Get the contents of an <code>InputStream</code> as a <code>byte[]</code>.
   */
  public static byte[] toByteArray (final InputStream input) throws IOException {
    return  toByteArray(input, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Get the contents of an <code>InputStream</code> as a <code>byte[]</code>.
   * @param bufferSize Size of internal buffer to use.
   */
  public static byte[] toByteArray (final InputStream input, final int bufferSize) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    copy(input, output, bufferSize);
    return  output.toByteArray();
  }

  ///////////////////////////////////////////////////////////////
  // Derived copy methods
  // Reader -> *
  ///////////////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////
  // Reader -> OutputStream
  /**
   * Serialize chars from a <code>Reader</code> to bytes on an <code>OutputStream</code>, and
   * flush the <code>OutputStream</code>.
   */
  public static void copy (final Reader input, final OutputStream output) throws IOException {
    copy(input, output, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Serialize chars from a <code>Reader</code> to bytes on an <code>OutputStream</code>, and
   * flush the <code>OutputStream</code>.
   * @param bufferSize Size of internal buffer to use.
   */
  public static void copy (final Reader input, final OutputStream output, final int bufferSize) throws IOException {
    OutputStreamWriter out = new OutputStreamWriter(output);
    copy(input, out, bufferSize);
    // NOTE: Unless anyone is planning on rewriting OutputStreamWriter, we have to flush
    // here.
    out.flush();
  }

  ///////////////////////////////////////////////////////////////
  // Reader -> String
  /**
   * Get the contents of a <code>Reader</code> as a String.
   */
  public static String toString (final Reader input) throws IOException {
    return  toString(input, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Get the contents of a <code>Reader</code> as a String.
   * @param bufferSize Size of internal buffer to use.
   */
  public static String toString (final Reader input, final int bufferSize) throws IOException {
    return  toString(input, bufferSize, 16);  // default initial internal buffer size
  }

  /**
   * Get the contents of a <code>Reader</code> as a String.
   * @param input
   * @param bufferSize Size of internal buffer to use.
   * @param estimatedLength
   */
  public static String toString (final Reader input, final int bufferSize,
      int estimatedLength) throws IOException {
    StringWriter sw = new StringWriter(estimatedLength);
    copy(input, sw, bufferSize);
    return  sw.toString();
  }

  ///////////////////////////////////////////////////////////////
  // Reader -> byte[]
  /**
   * Get the contents of a <code>Reader</code> as a <code>byte[]</code>.
   */
  public static byte[] toByteArray (final Reader input) throws IOException {
    return  toByteArray(input, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Get the contents of a <code>Reader</code> as a <code>byte[]</code>.
   * @param bufferSize Size of internal buffer to use.
   */
  public static byte[] toByteArray (final Reader input, final int bufferSize) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    copy(input, output, bufferSize);
    return  output.toByteArray();
  }

  ///////////////////////////////////////////////////////////////
  // Derived copy methods
  // String -> *
  ///////////////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////
  // String -> OutputStream
  /**
   * Serialize chars from a <code>String</code> to bytes on an <code>OutputStream</code>, and
   * flush the <code>OutputStream</code>.
   */
  public static void copy (final String input, final OutputStream output) throws IOException {
    copy(input, output, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Serialize chars from a <code>String</code> to bytes on an <code>OutputStream</code>, and
   * flush the <code>OutputStream</code>.
   * @param bufferSize Size of internal buffer to use.
   */
  public static void copy (final String input, final OutputStream output, final int bufferSize) throws IOException {
    StringReader in = new StringReader(input);
    OutputStreamWriter out = new OutputStreamWriter(output);
    copy(in, out, bufferSize);
    // NOTE: Unless anyone is planning on rewriting OutputStreamWriter, we have to flush
    // here.
    out.flush();
  }

  ///////////////////////////////////////////////////////////////
  // String -> Writer
  /**
   * Copy chars from a <code>String</code> to a <code>Writer</code>.
   */
  public static void copy (final String input, final Writer output) throws IOException {
    output.write(input);
  }

  /**
   * Copy bytes from an <code>InputStream</code> to an
   * <code>OutputStream</code>, with buffering.
   * This is equivalent to passing a
   * {@link java.io.BufferedInputStream} and
   * {@link java.io.BufferedOutputStream} to {@link #copy(InputStream, OutputStream)},
   * and flushing the output stream afterwards. The streams are not closed
   * after the copy.
   * @deprecated Buffering streams is actively harmful! See the class description as to why. Use
   * {@link #copy(InputStream, OutputStream)} instead.
   */
  public static void bufferedCopy (final InputStream input, final OutputStream output) throws IOException {
    BufferedInputStream in = new BufferedInputStream(input);
    BufferedOutputStream out = new BufferedOutputStream(output);
    copy(in, out);
    out.flush();
  }

  ///////////////////////////////////////////////////////////////
  // String -> byte[]
  /**
   * Get the contents of a <code>String</code> as a <code>byte[]</code>.
   */
  public static byte[] toByteArray (final String input) throws IOException {
    return  toByteArray(input, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Get the contents of a <code>String</code> as a <code>byte[]</code>.
   * @param bufferSize Size of internal buffer to use.
   */
  public static byte[] toByteArray (final String input, final int bufferSize) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    copy(input, output, bufferSize);
    return  output.toByteArray();
  }

  ///////////////////////////////////////////////////////////////
  // Derived copy methods
  // byte[] -> *
  ///////////////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////
  // byte[] -> Writer
  /**
   * Copy and convert bytes from a <code>byte[]</code> to chars on a
   * <code>Writer</code>.
   * The platform's default encoding is used for the byte-to-char conversion.
   */
  public static void copy (final byte[] input, final Writer output) throws IOException {
    copy(input, output, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Copy and convert bytes from a <code>byte[]</code> to chars on a
   * <code>Writer</code>.
   * The platform's default encoding is used for the byte-to-char conversion.
   * @param bufferSize Size of internal buffer to use.
   */
  public static void copy (final byte[] input, final Writer output, final int bufferSize) throws IOException {
    ByteArrayInputStream in = new ByteArrayInputStream(input);
    copy(in, output, bufferSize);
  }

  /**
   * Copy and convert bytes from a <code>byte[]</code> to chars on a
   * <code>Writer</code>, using the specified encoding.
   * @param encoding The name of a supported character encoding. See the
   * <a href="http://www.iana.org/assignments/character-sets">IANA
   * Charset Registry</a> for a list of valid encoding types.
   */
  public static void copy (final byte[] input, final Writer output, final String encoding) throws IOException {
    ByteArrayInputStream in = new ByteArrayInputStream(input);
    copy(in, output, encoding);
  }

  /**
   * Copy and convert bytes from a <code>byte[]</code> to chars on a
   * <code>Writer</code>, using the specified encoding.
   * @param encoding The name of a supported character encoding. See the
   *        <a href="http://www.iana.org/assignments/character-sets">IANA
   *        Charset Registry</a> for a list of valid encoding types.
   * @param bufferSize Size of internal buffer to use.
   */
  public static void copy (final byte[] input, final Writer output, final String encoding,
      final int bufferSize) throws IOException {
    ByteArrayInputStream in = new ByteArrayInputStream(input);
    copy(in, output, encoding, bufferSize);
  }

  ///////////////////////////////////////////////////////////////
  // byte[] -> String
  /**
   * Get the contents of a <code>byte[]</code> as a String.
   * The platform's default encoding is used for the byte-to-char conversion.
   */
  public static String toString (final byte[] input) throws IOException {
    return  toString(input, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Get the contents of a <code>byte[]</code> as a String.
   * The platform's default encoding is used for the byte-to-char conversion.
   * @param bufferSize Size of internal buffer to use.
   */
  public static String toString (final byte[] input, final int bufferSize) throws IOException {
    StringWriter sw = new StringWriter();
    copy(input, sw, bufferSize);
    return  sw.toString();
  }

  /**
   * Get the contents of a <code>byte[]</code> as a String.
   * @param encoding The name of a supported character encoding. See the
   *    <a href="http://www.iana.org/assignments/character-sets">IANA
   *    Charset Registry</a> for a list of valid encoding types.
   */
  public static String toString (final byte[] input, final String encoding) throws IOException {
    return  toString(input, encoding, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Get the contents of a <code>byte[]</code> as a String.
   * @param encoding The name of a supported character encoding. See the
   *   <a href="http://www.iana.org/assignments/character-sets">IANA
   *   Charset Registry</a> for a list of valid encoding types.
   * @param bufferSize Size of internal buffer to use.
   */
  public static String toString (final byte[] input, final String encoding,
      final int bufferSize) throws IOException {
    StringWriter sw = new StringWriter();
    copy(input, sw, encoding, bufferSize);
    return  sw.toString();
  }

  ///////////////////////////////////////////////////////////////
  // byte[] -> OutputStream
  /**
   * Copy bytes from a <code>byte[]</code> to an <code>OutputStream</code>.
   */
  public static void copy (final byte[] input, final OutputStream output) throws IOException {
    copy(input, output, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Copy bytes from a <code>byte[]</code> to an <code>OutputStream</code>.
   * @param bufferSize Size of internal buffer to use.
   */
  public static void copy (final byte[] input, final OutputStream output, final int bufferSize) throws IOException {
    output.write(input);
  }

  /**
   *
   * @param inputStream
   * @return
   * @throws IOException
   */
  public static InputStream getResetSupportingInputStream (InputStream inputStream) throws IOException {
    // test stream
    if (inputStream.markSupported()) {
      return  inputStream;
    }
    // read stream into output stream
    ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(inputStream.available()
        + 1);
    copy(inputStream, byteOutputStream);
    // wrap byte array output stream in input stream
    byte[] byteArr = byteOutputStream.toByteArray();
    InputStream newInStream = new ByteArrayInputStream(byteArr);
    return  newInStream;
  }

  public static void close(InputStream in) {
	  if (in != null) {
		  try {
			  in.close();
		  } catch (IOException e) {}
	  }
  }
}




