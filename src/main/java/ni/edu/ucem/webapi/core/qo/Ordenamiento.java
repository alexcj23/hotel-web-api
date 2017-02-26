package ni.edu.ucem.webapi.core.qo;

import ni.edu.ucem.webapi.modelo.Filtro.SortOrder;

public class Ordenamiento {

	private String field;
	private SortOrder order;

	public Ordenamiento(String field, SortOrder order) {
		this.field = field;
		this.order = order;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public SortOrder getOrder() {
		return order;
	}

	public void setOrder(SortOrder order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return " " + field + " " + order.getValue() + "]";
	}

}
