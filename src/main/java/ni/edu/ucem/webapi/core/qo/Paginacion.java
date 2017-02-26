package ni.edu.ucem.webapi.core.qo;

public class Paginacion {

	public static final int DEFAULT_OFFSET = 0;
	public static final int DEFAULT_LIMIT = 10;

	private int offset;
	private int limit;

	public Paginacion() {
		this.offset = DEFAULT_OFFSET;
		this.limit = DEFAULT_LIMIT;
	}

	public Paginacion(int offset, int limit) {
		this.offset = offset;
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
