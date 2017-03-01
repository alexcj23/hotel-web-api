package ni.edu.ucem.webapi.core.qo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ni.edu.ucem.webapi.modelo.Filtro.SortOrder;

public class QueryOption {

	private static final String REQUEST_PARAM_PAGING = "paging";
	private static final String REQUEST_PARAM_ORDER = "order";

	private StringBuilder pagination;
	private StringBuilder order;
	private StringBuilder selection;
	private StringBuilder filter;
	private List<String> params;
	private Class<?> inferedClass;
	private Paginacion page;

	public QueryOption(HttpServletRequest request, Class<?> type) {
		this.pagination = new StringBuilder();
		this.order = new StringBuilder();
		this.selection = new StringBuilder();
		this.filter = new StringBuilder();
		this.page = new Paginacion();
		this.inferedClass = type;
		this.params = ((request.getQueryString() != null) ? Arrays.asList(request.getQueryString().split("&"))
				: new ArrayList<String>());
	}

	private Paginacion getRequestPaging() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		for (String param : this.params) {
			if (param.split("=")[0].equalsIgnoreCase(REQUEST_PARAM_PAGING)) {
				String prmt = param.split("=")[1];
				String pagns = java.net.URLDecoder.decode(prmt, "UTF-8");
				String json = "{".concat(Util.convert(pagns).concat("}"));
				page = mapper.readValue(json, Paginacion.class);
			}
		}
		return page;
	}

	private Map<String, SortOrder> getRequestOrder() throws UnsupportedEncodingException {
		Map<String, SortOrder> orders = new HashMap<String, SortOrder>();
		for (String param : this.params) {
			if (param.split("=")[0].equalsIgnoreCase(REQUEST_PARAM_ORDER)) {
				String prmt = param.split("=")[1];
				String ordinances = java.net.URLDecoder.decode(prmt, "UTF-8").replace("[", "").replace("]", "");
				if (ordinances.indexOf(":") != -1) {
					for (String order : ordinances.split(",")) {
						String[] os = order.split(":");
						orders.put(os[0], SortOrder.enumOf(os[1]));
					}
				}
			}
		}
		return orders;
	}

	private QueryOption paginacion() throws JsonParseException, JsonMappingException, IOException {
		pagination.setLength(0);
		Paginacion paginacion = (Util.exist(params, REQUEST_PARAM_PAGING) ? this.getRequestPaging() : page);
		pagination.append(
				String.format(" limit %d offset %d", new Object[] { paginacion.getLimit(), paginacion.getOffset() }));
		return this;
	}

	private QueryOption ordenamiento() throws UnsupportedEncodingException {
		order.setLength(0);
		if (Util.exist(params, REQUEST_PARAM_ORDER)) {
			Map<String, SortOrder> orders = this.getRequestOrder();
			List<String> orderFields = new ArrayList<String>();
			for (Entry<String, SortOrder> order : orders.entrySet()) {
				orderFields.add(order.getKey() + " " + order.getValue());
			}
			if (!orderFields.isEmpty()) {
				order.append(" order by ");
				for (String o : orderFields) {
					order.append(o.concat((orderFields.indexOf(o) == (orderFields.size() - 1)) ? "" : ", "));
				}
			}
		}
		return this;
	}

	public String build() {
		try {
			this.ordenamiento().paginacion();
			StringBuilder query = new StringBuilder();
			query.append(" select ".concat(this.selection.toString().isEmpty() ? "*" : this.selection.toString()));
			query.append(" from ".concat(inferedClass.getSimpleName().toLowerCase()));
			query.append(filter.toString());
			query.append(order.toString());
			query.append(pagination.toString());
			return query.toString();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setFilter(String where) {
		this.filter.append(where);
	}

	public int getOffset() {
		return this.page.getOffset();
	}

	public int getLimit() {
		return this.page.getLimit();
	}
}
