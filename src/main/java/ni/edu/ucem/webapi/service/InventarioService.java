package ni.edu.ucem.webapi.service;

import java.util.List;

import ni.edu.ucem.webapi.core.qo.QueryOption;
import ni.edu.ucem.webapi.modelo.CategoriaCuarto;
import ni.edu.ucem.webapi.modelo.Cuarto;
import ni.edu.ucem.webapi.modelo.Pagina;

public interface InventarioService 
{
    public void agregarCategoriaCuarto(final CategoriaCuarto pCategoriaCuarto);

    public void guardarCategoriaCuarto(final CategoriaCuarto pCategoriaCuarto);

    public void eliminarCategoriaCuarto(int id);
    
    public CategoriaCuarto obtenerCategoriaCuarto(final int id);

    public List<CategoriaCuarto> obtenerTodosCategoriaCuartos();

    public void agregarCuarto(final Cuarto pCuarto);

    public void guardarCuarto(final Cuarto pCuarto);

    public void eliminarCuarto(final int pCuarto);

    public Cuarto obtenerCuarto(final int pCuarto);

    public Pagina<Cuarto> obtenerTodosCuarto(final QueryOption options);

    public Pagina<Cuarto> obtenerTodosCuartoEnCategoria(final int pCategoriaCuarto, QueryOption queryOption);
    
}
