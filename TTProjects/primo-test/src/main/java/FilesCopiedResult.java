
import java.util.Date;

public class FilesCopiedResult {

	private int numCopied;
	private Date maxCopiedFileDate;
	
	public FilesCopiedResult(int numCopied, Date maxCopiedFileDate) {
		this.maxCopiedFileDate = maxCopiedFileDate;
		this.numCopied = numCopied;
	}
	
	public int getNumCopied() {
		return numCopied;
	}
	public Date getMaxCopiedFileDate() {
		return maxCopiedFileDate;
	}
	
	
	
}
