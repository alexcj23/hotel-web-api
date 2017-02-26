package ni.edu.ucem.webapi.dao;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import ni.edu.ucem.webapi.modelo.CategoriaCuarto;

public interface CategoriaCuartoDAO 
{
    @PreAuthorize("hasRole('ADMIN')")
    public CategoriaCuarto obtenerPorId(final int pId);

    public List<CategoriaCuarto> obtenerTodos();

    public void agregar(final CategoriaCuarto pCategoriaCuarto);

    public void guardar(final CategoriaCuarto pCategoriaCuarto);

    public void eliminar(final int pId);
}
