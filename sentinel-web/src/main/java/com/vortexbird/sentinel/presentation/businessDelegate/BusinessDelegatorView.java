package com.vortexbird.sentinel.presentation.businessDelegate;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.vortexbird.sentinel.modelo.SegAuditoria;
import com.vortexbird.sentinel.modelo.SegCambioPass;
import com.vortexbird.sentinel.modelo.SegCompania;
import com.vortexbird.sentinel.modelo.SegGrupoOpcion;
import com.vortexbird.sentinel.modelo.SegHistorialConstrasena;
import com.vortexbird.sentinel.modelo.SegOpcion;
import com.vortexbird.sentinel.modelo.SegParametro;
import com.vortexbird.sentinel.modelo.SegPermiso;
import com.vortexbird.sentinel.modelo.SegRol;
import com.vortexbird.sentinel.modelo.SegRolUsuario;
import com.vortexbird.sentinel.modelo.SegSistema;
import com.vortexbird.sentinel.modelo.SegSistemaCia;
import com.vortexbird.sentinel.modelo.SegSucursal;
import com.vortexbird.sentinel.modelo.SegUsuario;
import com.vortexbird.sentinel.modelo.VinCamposParametrizables;
import com.vortexbird.sentinel.modelo.control.ILoginLogic;
import com.vortexbird.sentinel.modelo.control.ISegAuditoriaLogic;
import com.vortexbird.sentinel.modelo.control.ISegCambioPassLogic;
import com.vortexbird.sentinel.modelo.control.ISegCompaniaLogic;
import com.vortexbird.sentinel.modelo.control.ISegGrupoOpcionLogic;
import com.vortexbird.sentinel.modelo.control.ISegHistorialConstrasenaLogic;
import com.vortexbird.sentinel.modelo.control.ISegOpcionLogic;
import com.vortexbird.sentinel.modelo.control.ISegParametroLogic;
import com.vortexbird.sentinel.modelo.control.ISegPermisoLogic;
import com.vortexbird.sentinel.modelo.control.ISegRolLogic;
import com.vortexbird.sentinel.modelo.control.ISegRolUsuarioLogic;
import com.vortexbird.sentinel.modelo.control.ISegSistemaCiaLogic;
import com.vortexbird.sentinel.modelo.control.ISegSistemaLogic;
import com.vortexbird.sentinel.modelo.control.ISegSucursalLogic;
import com.vortexbird.sentinel.modelo.control.ISegUsuarioLogic;
import com.vortexbird.sentinel.modelo.control.IVinCamposParametrizablesLogic;
import com.vortexbird.sentinel.modelo.dto.GrupoDTO;
import com.vortexbird.sentinel.modelo.dto.SegAuditoriaDTO;
import com.vortexbird.sentinel.modelo.dto.SegCambioPassDTO;
import com.vortexbird.sentinel.modelo.dto.SegCompaniaDTO;
import com.vortexbird.sentinel.modelo.dto.SegGrupoOpcionDTO;
import com.vortexbird.sentinel.modelo.dto.SegHistorialConstrasenaDTO;
import com.vortexbird.sentinel.modelo.dto.SegOpcionDTO;
import com.vortexbird.sentinel.modelo.dto.SegParametroDTO;
import com.vortexbird.sentinel.modelo.dto.SegPermisoDTO;
import com.vortexbird.sentinel.modelo.dto.SegRolDTO;
import com.vortexbird.sentinel.modelo.dto.SegRolUsuarioDTO;
import com.vortexbird.sentinel.modelo.dto.SegSistemaCiaDTO;
import com.vortexbird.sentinel.modelo.dto.SegSistemaDTO;
import com.vortexbird.sentinel.modelo.dto.SegSucursalDTO;
import com.vortexbird.sentinel.modelo.dto.SegUsuarioDTO;
import com.vortexbird.sentinel.modelo.dto.SellPersonaDTO;
import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private ISegAuditoriaLogic segAuditoriaLogic;
    @Autowired
    private ISegCambioPassLogic segCambioPassLogic;
    @Autowired
    private ISegCompaniaLogic segCompaniaLogic;
    @Autowired
    private ISegGrupoOpcionLogic segGrupoOpcionLogic;
    @Autowired
    private ISegHistorialConstrasenaLogic segHistorialConstrasenaLogic;
    @Autowired
    private ISegOpcionLogic segOpcionLogic;
    @Autowired
    private ISegParametroLogic segParametroLogic;
    @Autowired
    private ISegPermisoLogic segPermisoLogic;
    @Autowired
    private ISegRolLogic segRolLogic;
    @Autowired
    private ISegRolUsuarioLogic segRolUsuarioLogic;
    @Autowired
    private ISegSistemaLogic segSistemaLogic;
    @Autowired
    private ISegSistemaCiaLogic segSistemaCiaLogic;
    @Autowired
    private ISegSucursalLogic segSucursalLogic;
    @Autowired
    private ISegUsuarioLogic segUsuarioLogic;
    @Autowired
    private ILoginLogic loginLogic;
    @Autowired
    private IVinCamposParametrizablesLogic vinCamposParametrizablesLogic;

    public List<SegAuditoria> getSegAuditoria() throws Exception {
        return segAuditoriaLogic.getSegAuditoria();
    }

    public void saveSegAuditoria(SegAuditoria entity) throws Exception {
        segAuditoriaLogic.saveSegAuditoria(entity);
    }

    public void deleteSegAuditoria(SegAuditoria entity)
        throws Exception {
        segAuditoriaLogic.deleteSegAuditoria(entity);
    }

    public void updateSegAuditoria(SegAuditoria entity)
        throws Exception {
        segAuditoriaLogic.updateSegAuditoria(entity);
    }

    public SegAuditoria getSegAuditoria(Long autCodigo)
        throws Exception {
        SegAuditoria segAuditoria = null;

        try {
            segAuditoria = segAuditoriaLogic.getSegAuditoria(autCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segAuditoria;
    }

    public List<SegAuditoria> findByCriteriaInSegAuditoria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return segAuditoriaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegAuditoria> findPageSegAuditoria(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segAuditoriaLogic.findPageSegAuditoria(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSegAuditoria() throws Exception {
        return segAuditoriaLogic.findTotalNumberSegAuditoria();
    }

    public List<SegAuditoriaDTO> getDataSegAuditoria()
        throws Exception {
        return segAuditoriaLogic.getDataSegAuditoria();
    }

    public List<SegCambioPass> getSegCambioPass() throws Exception {
        return segCambioPassLogic.getSegCambioPass();
    }

    public void saveSegCambioPass(SegCambioPass entity)
        throws Exception {
        segCambioPassLogic.saveSegCambioPass(entity);
    }

    public void deleteSegCambioPass(SegCambioPass entity)
        throws Exception {
        segCambioPassLogic.deleteSegCambioPass(entity);
    }

    public void updateSegCambioPass(SegCambioPass entity)
        throws Exception {
        segCambioPassLogic.updateSegCambioPass(entity);
    }

    public SegCambioPass getSegCambioPass(Long capCodigo)
        throws Exception {
        SegCambioPass segCambioPass = null;

        try {
            segCambioPass = segCambioPassLogic.getSegCambioPass(capCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segCambioPass;
    }

    public List<SegCambioPass> findByCriteriaInSegCambioPass(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return segCambioPassLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegCambioPass> findPageSegCambioPass(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segCambioPassLogic.findPageSegCambioPass(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSegCambioPass() throws Exception {
        return segCambioPassLogic.findTotalNumberSegCambioPass();
    }

    public List<SegCambioPassDTO> getDataSegCambioPass()
        throws Exception {
        return segCambioPassLogic.getDataSegCambioPass();
    }

    public List<SegCompania> getSegCompania() throws Exception {
        return segCompaniaLogic.getSegCompania();
    }

    public void saveSegCompania(SegCompania entity) throws Exception {
        segCompaniaLogic.saveSegCompania(entity);
    }

    public void deleteSegCompania(SegCompania entity) throws Exception {
        segCompaniaLogic.deleteSegCompania(entity);
    }

    public void updateSegCompania(SegCompania entity) throws Exception {
        segCompaniaLogic.updateSegCompania(entity);
    }

    public SegCompania getSegCompania(Long ciaCodigo) throws Exception {
        SegCompania segCompania = null;

        try {
            segCompania = segCompaniaLogic.getSegCompania(ciaCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segCompania;
    }

    public List<SegCompania> findByCriteriaInSegCompania(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return segCompaniaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegCompania> findPageSegCompania(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segCompaniaLogic.findPageSegCompania(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSegCompania() throws Exception {
        return segCompaniaLogic.findTotalNumberSegCompania();
    }

    public List<SegCompaniaDTO> getDataSegCompania() throws Exception {
        return segCompaniaLogic.getDataSegCompania();
    }

    public List<SegGrupoOpcion> getSegGrupoOpcion() throws Exception {
        return segGrupoOpcionLogic.getSegGrupoOpcion();
    }

    public void saveSegGrupoOpcion(SegGrupoOpcion entity)
        throws Exception {
        segGrupoOpcionLogic.saveSegGrupoOpcion(entity);
    }

    public void deleteSegGrupoOpcion(SegGrupoOpcion entity)
        throws Exception {
        segGrupoOpcionLogic.deleteSegGrupoOpcion(entity);
    }

    public void updateSegGrupoOpcion(SegGrupoOpcion entity)
        throws Exception {
        segGrupoOpcionLogic.updateSegGrupoOpcion(entity);
    }

    public SegGrupoOpcion getSegGrupoOpcion(Long gruCodigo)
        throws Exception {
        SegGrupoOpcion segGrupoOpcion = null;

        try {
            segGrupoOpcion = segGrupoOpcionLogic.getSegGrupoOpcion(gruCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segGrupoOpcion;
    }

    public List<SegGrupoOpcion> findByCriteriaInSegGrupoOpcion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return segGrupoOpcionLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegGrupoOpcion> findPageSegGrupoOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segGrupoOpcionLogic.findPageSegGrupoOpcion(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSegGrupoOpcion() throws Exception {
        return segGrupoOpcionLogic.findTotalNumberSegGrupoOpcion();
    }

    public List<SegGrupoOpcionDTO> getDataSegGrupoOpcion()
        throws Exception {
        return segGrupoOpcionLogic.getDataSegGrupoOpcion();
    }

    public List<SegHistorialConstrasena> getSegHistorialConstrasena()
        throws Exception {
        return segHistorialConstrasenaLogic.getSegHistorialConstrasena();
    }

    public void saveSegHistorialConstrasena(SegHistorialConstrasena entity)
        throws Exception {
        segHistorialConstrasenaLogic.saveSegHistorialConstrasena(entity);
    }

    public void deleteSegHistorialConstrasena(SegHistorialConstrasena entity)
        throws Exception {
        segHistorialConstrasenaLogic.deleteSegHistorialConstrasena(entity);
    }

    public void updateSegHistorialConstrasena(SegHistorialConstrasena entity)
        throws Exception {
        segHistorialConstrasenaLogic.updateSegHistorialConstrasena(entity);
    }

    public SegHistorialConstrasena getSegHistorialConstrasena(Long hcoCodigo)
        throws Exception {
        SegHistorialConstrasena segHistorialConstrasena = null;

        try {
            segHistorialConstrasena = segHistorialConstrasenaLogic.getSegHistorialConstrasena(hcoCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segHistorialConstrasena;
    }

    public List<SegHistorialConstrasena> findByCriteriaInSegHistorialConstrasena(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return segHistorialConstrasenaLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<SegHistorialConstrasena> findPageSegHistorialConstrasena(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return segHistorialConstrasenaLogic.findPageSegHistorialConstrasena(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSegHistorialConstrasena()
        throws Exception {
        return segHistorialConstrasenaLogic.findTotalNumberSegHistorialConstrasena();
    }

    public List<SegHistorialConstrasenaDTO> getDataSegHistorialConstrasena()
        throws Exception {
        return segHistorialConstrasenaLogic.getDataSegHistorialConstrasena();
    }

    public List<SegOpcion> getSegOpcion() throws Exception {
        return segOpcionLogic.getSegOpcion();
    }

    public void saveSegOpcion(SegOpcion entity) throws Exception {
        segOpcionLogic.saveSegOpcion(entity);
    }

    public void deleteSegOpcion(SegOpcion entity) throws Exception {
        segOpcionLogic.deleteSegOpcion(entity);
    }

    public void updateSegOpcion(SegOpcion entity) throws Exception {
        segOpcionLogic.updateSegOpcion(entity);
    }

    public SegOpcion getSegOpcion(Long opcCodigo) throws Exception {
        SegOpcion segOpcion = null;

        try {
            segOpcion = segOpcionLogic.getSegOpcion(opcCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segOpcion;
    }

    public List<SegOpcion> findByCriteriaInSegOpcion(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return segOpcionLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegOpcion> findPageSegOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segOpcionLogic.findPageSegOpcion(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberSegOpcion() throws Exception {
        return segOpcionLogic.findTotalNumberSegOpcion();
    }

    public List<SegOpcionDTO> getDataSegOpcion() throws Exception {
        return segOpcionLogic.getDataSegOpcion();
    }

    public List<SegParametro> getSegParametro() throws Exception {
        return segParametroLogic.getSegParametro();
    }

    public void saveSegParametro(SegParametro entity) throws Exception {
        segParametroLogic.saveSegParametro(entity);
    }

    public void deleteSegParametro(SegParametro entity)
        throws Exception {
        segParametroLogic.deleteSegParametro(entity);
    }

    public void updateSegParametro(SegParametro entity)
        throws Exception {
        segParametroLogic.updateSegParametro(entity);
    }

    public SegParametro getSegParametro(Long parCodigo)
        throws Exception {
        SegParametro segParametro = null;

        try {
            segParametro = segParametroLogic.getSegParametro(parCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segParametro;
    }

    public List<SegParametro> findByCriteriaInSegParametro(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return segParametroLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegParametro> findPageSegParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segParametroLogic.findPageSegParametro(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSegParametro() throws Exception {
        return segParametroLogic.findTotalNumberSegParametro();
    }

    public List<SegParametroDTO> getDataSegParametro()
        throws Exception {
        return segParametroLogic.getDataSegParametro();
    }

    public List<SegPermiso> getSegPermiso() throws Exception {
        return segPermisoLogic.getSegPermiso();
    }

    public void saveSegPermiso(SegPermiso entity) throws Exception {
        segPermisoLogic.saveSegPermiso(entity);
    }

    public void deleteSegPermiso(SegPermiso entity) throws Exception {
        segPermisoLogic.deleteSegPermiso(entity);
    }

    public void updateSegPermiso(SegPermiso entity) throws Exception {
        segPermisoLogic.updateSegPermiso(entity);
    }

    public SegPermiso getSegPermiso(Long perCodigo) throws Exception {
        SegPermiso segPermiso = null;

        try {
            segPermiso = segPermisoLogic.getSegPermiso(perCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segPermiso;
    }

    public List<SegPermiso> findByCriteriaInSegPermiso(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return segPermisoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegPermiso> findPageSegPermiso(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segPermisoLogic.findPageSegPermiso(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSegPermiso() throws Exception {
        return segPermisoLogic.findTotalNumberSegPermiso();
    }

    public List<SegPermisoDTO> getDataSegPermiso() throws Exception {
        return segPermisoLogic.getDataSegPermiso();
    }

    public List<SegRol> getSegRol() throws Exception {
        return segRolLogic.getSegRol();
    }

    public void saveSegRol(SegRol entity) throws Exception {
        segRolLogic.saveSegRol(entity);
    }

    public void deleteSegRol(SegRol entity) throws Exception {
        segRolLogic.deleteSegRol(entity);
    }

    public void updateSegRol(SegRol entity) throws Exception {
        segRolLogic.updateSegRol(entity);
    }

    public SegRol getSegRol(Long rolCodigo) throws Exception {
        SegRol segRol = null;

        try {
            segRol = segRolLogic.getSegRol(rolCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segRol;
    }

    public List<SegRol> findByCriteriaInSegRol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return segRolLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegRol> findPageSegRol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segRolLogic.findPageSegRol(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberSegRol() throws Exception {
        return segRolLogic.findTotalNumberSegRol();
    }

    public List<SegRolDTO> getDataSegRol() throws Exception {
        return segRolLogic.getDataSegRol();
    }

    public List<SegRolUsuario> getSegRolUsuario() throws Exception {
        return segRolUsuarioLogic.getSegRolUsuario();
    }

    public void saveSegRolUsuario(SegRolUsuario entity)
        throws Exception {
        segRolUsuarioLogic.saveSegRolUsuario(entity);
    }

    public void deleteSegRolUsuario(SegRolUsuario entity)
        throws Exception {
        segRolUsuarioLogic.deleteSegRolUsuario(entity);
    }

    public void updateSegRolUsuario(SegRolUsuario entity)
        throws Exception {
        segRolUsuarioLogic.updateSegRolUsuario(entity);
    }

    public SegRolUsuario getSegRolUsuario(Long rluCodigo)
        throws Exception {
        SegRolUsuario segRolUsuario = null;

        try {
            segRolUsuario = segRolUsuarioLogic.getSegRolUsuario(rluCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segRolUsuario;
    }

    public List<SegRolUsuario> findByCriteriaInSegRolUsuario(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return segRolUsuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegRolUsuario> findPageSegRolUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segRolUsuarioLogic.findPageSegRolUsuario(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSegRolUsuario() throws Exception {
        return segRolUsuarioLogic.findTotalNumberSegRolUsuario();
    }

    public List<SegRolUsuarioDTO> getDataSegRolUsuario()
        throws Exception {
        return segRolUsuarioLogic.getDataSegRolUsuario();
    }

    public List<SegSistema> getSegSistema() throws Exception {
        return segSistemaLogic.getSegSistema();
    }

    public void saveSegSistema(SegSistema entity) throws Exception {
        segSistemaLogic.saveSegSistema(entity);
    }

    public void deleteSegSistema(SegSistema entity) throws Exception {
        segSistemaLogic.deleteSegSistema(entity);
    }

    public void updateSegSistema(SegSistema entity) throws Exception {
        segSistemaLogic.updateSegSistema(entity);
    }

    public SegSistema getSegSistema(Long sisCodigo) throws Exception {
        SegSistema segSistema = null;

        try {
            segSistema = segSistemaLogic.getSegSistema(sisCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segSistema;
    }

    public List<SegSistema> findByCriteriaInSegSistema(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return segSistemaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegSistema> findPageSegSistema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segSistemaLogic.findPageSegSistema(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSegSistema() throws Exception {
        return segSistemaLogic.findTotalNumberSegSistema();
    }

    public List<SegSistemaDTO> getDataSegSistema() throws Exception {
        return segSistemaLogic.getDataSegSistema();
    }

    public List<SegSistemaCia> getSegSistemaCia() throws Exception {
        return segSistemaCiaLogic.getSegSistemaCia();
    }

    public void saveSegSistemaCia(SegSistemaCia entity)
        throws Exception {
        segSistemaCiaLogic.saveSegSistemaCia(entity);
    }

    public void deleteSegSistemaCia(SegSistemaCia entity)
        throws Exception {
        segSistemaCiaLogic.deleteSegSistemaCia(entity);
    }

    public void updateSegSistemaCia(SegSistemaCia entity)
        throws Exception {
        segSistemaCiaLogic.updateSegSistemaCia(entity);
    }

    public SegSistemaCia getSegSistemaCia(Long sicCodigo)
        throws Exception {
        SegSistemaCia segSistemaCia = null;

        try {
            segSistemaCia = segSistemaCiaLogic.getSegSistemaCia(sicCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segSistemaCia;
    }

    public List<SegSistemaCia> findByCriteriaInSegSistemaCia(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return segSistemaCiaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegSistemaCia> findPageSegSistemaCia(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segSistemaCiaLogic.findPageSegSistemaCia(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSegSistemaCia() throws Exception {
        return segSistemaCiaLogic.findTotalNumberSegSistemaCia();
    }

    public List<SegSistemaCiaDTO> getDataSegSistemaCia()
        throws Exception {
        return segSistemaCiaLogic.getDataSegSistemaCia();
    }

    public List<SegSucursal> getSegSucursal() throws Exception {
        return segSucursalLogic.getSegSucursal();
    }

    public void saveSegSucursal(SegSucursal entity) throws Exception {
        segSucursalLogic.saveSegSucursal(entity);
    }

    public void deleteSegSucursal(SegSucursal entity) throws Exception {
        segSucursalLogic.deleteSegSucursal(entity);
    }

    public void updateSegSucursal(SegSucursal entity) throws Exception {
        segSucursalLogic.updateSegSucursal(entity);
    }

    public SegSucursal getSegSucursal(Long sucCodigo) throws Exception {
        SegSucursal segSucursal = null;

        try {
            segSucursal = segSucursalLogic.getSegSucursal(sucCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segSucursal;
    }

    public List<SegSucursal> findByCriteriaInSegSucursal(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return segSucursalLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegSucursal> findPageSegSucursal(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segSucursalLogic.findPageSegSucursal(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSegSucursal() throws Exception {
        return segSucursalLogic.findTotalNumberSegSucursal();
    }

    public List<SegSucursalDTO> getDataSegSucursal() throws Exception {
        return segSucursalLogic.getDataSegSucursal();
    }

    public List<SegUsuario> getSegUsuario() throws Exception {
        return segUsuarioLogic.getSegUsuario();
    }

    public void saveSegUsuario(SegUsuario entity) throws Exception {
        segUsuarioLogic.saveSegUsuario(entity);
    }

    public void deleteSegUsuario(SegUsuario entity) throws Exception {
        segUsuarioLogic.deleteSegUsuario(entity);
    }

    public void updateSegUsuario(SegUsuario entity) throws Exception {
        segUsuarioLogic.updateSegUsuario(entity);
    }

    public SegUsuario getSegUsuario(Long usuCodigo) throws Exception {
        SegUsuario segUsuario = null;

        try {
            segUsuario = segUsuarioLogic.getSegUsuario(usuCodigo);
        } catch (Exception e) {
            throw e;
        }

        return segUsuario;
    }

    public List<SegUsuario> findByCriteriaInSegUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return segUsuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SegUsuario> findPageSegUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return segUsuarioLogic.findPageSegUsuario(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSegUsuario() throws Exception {
        return segUsuarioLogic.findTotalNumberSegUsuario();
    }

    public List<SegUsuarioDTO> getDataSegUsuario() throws Exception {
        return segUsuarioLogic.getDataSegUsuario();
    }

    @Override
    public UsuarioDTO autenticar(String login, String pass, String dominio) throws Exception{
		return loginLogic.autenticar(login, pass, dominio);
	}
    
    @Override
    public List<SegSistema> consultarSistemasDeUsuarioAdministrador(Long usuCodigo) throws Exception{
    	return segSistemaLogic.consultarSistemasDeUsuarioAdministrador(usuCodigo);
    }
    
    @Override
    public List<SegCompania> consultarCompaniasDeDeusuarioAdministrador(Long ucuCodigo, long sisCodigo) throws Exception{
    	return segCompaniaLogic.consultarCompaniasDeDeusuarioAdministrador(ucuCodigo, sisCodigo);
    }
    
    @Override
    public List<GrupoDTO> consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(String login,String sistema, String sucursal, String cia) {
    	return segOpcionLogic.consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(login, sistema, sucursal, cia);
    }
    
    @Override
    public void guardarOpcion(String nombre, String descripcion, String llaveAcceso, Long gruCodigo, Long usuCreador, String estadoRegistro, Integer orden) throws Exception{
    	segOpcionLogic.guardarOpcion(nombre, descripcion, llaveAcceso, gruCodigo, usuCreador, estadoRegistro, orden);
    }
    
    @Override
    public List<SegOpcionDTO> consultarOpcionesPorSistema(Long sisCodigo) throws Exception {
    	return segOpcionLogic.consultarOpcionesPorSistema(sisCodigo);
    }
    
    @Override
    public List<SegGrupoOpcion> consultarGrupoOpcionesPorSistema(Long sisCodigo) throws Exception{
    	return segGrupoOpcionLogic.consultarGrupoOpcionesPorSistema(sisCodigo);
    }
    
    @Override
    public void modificarOpcion(Long opcCodigo, String nombre, String descripcion,
			String llaveAcceso, Long gruCodigo, Long usuCreador,
			String estadoRegistro, Integer orden) throws Exception{
    	segOpcionLogic.modificarOpcion(opcCodigo, nombre, descripcion, llaveAcceso, gruCodigo, usuCreador, estadoRegistro, orden);
    }
    
    @Override
    public void guardarGrupoOpcion(Long gruCodigo, String gruDescripcion,
			String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
			Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception{
    	segGrupoOpcionLogic.guardarGrupoOpcion(gruCodigo, gruDescripcion, gruEstadoRegistro, gruLlaveAcceso, gruNombre, gruCodigo_SegGrupoOpcion, sisCodigo_SegSistema, usuCodigo_SegUsuario);
    }
    
    @Override
    public void modificarGrupoOpcion(Long gruCodigo, String gruDescripcion,
			String gruEstadoRegistro, String gruLlaveAcceso, String gruNombre,
			Long gruCodigo_SegGrupoOpcion, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario) throws Exception{
    
    	segGrupoOpcionLogic.modificarGrupoOpcion(gruCodigo, gruDescripcion, gruEstadoRegistro, gruLlaveAcceso, gruNombre, gruCodigo_SegGrupoOpcion, sisCodigo_SegSistema, usuCodigo_SegUsuario);
    }
    
    @Override
    public void guardarRol(String rolDescripcion, Long rolDiasCaducidadPwd,
			String rolEstadoRegistro, String rolNombre,
			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario, String esAdmonDeSistema)
			throws Exception{
    	
    	segRolLogic.guardarRol(rolDescripcion, rolDiasCaducidadPwd, rolEstadoRegistro, rolNombre, sisCodigo_SegSistema, usuCodigo_SegUsuario, esAdmonDeSistema);
    	
    }

    @Override
	public void modificarRol(Long rolCodigo, String rolDescripcion,
			Long rolDiasCaducidadPwd, String rolEstadoRegistro,
			String rolNombre, Long sisCodigo_SegSistema,
			Long usuCodigo_SegUsuario, String esAdmonDeSistema) throws Exception {
    	
    	segRolLogic.modificarRol(rolCodigo, rolDescripcion, rolDiasCaducidadPwd, rolEstadoRegistro, rolNombre, sisCodigo_SegSistema, usuCodigo_SegUsuario, esAdmonDeSistema);
    	
    }
    
    @Override
    public List<SegRol> consultarRolesPorSistema(Long sisCodigo) throws Exception{
    	return segRolLogic.consultarRolesPorSistema(sisCodigo);
    }
    
    @Override
    public List<SegRolDTO> consultarRolesPorSistemaDTO(Long sisCodigo) throws Exception{
    	return segRolLogic.consultarRolesPorSistemaDTO(sisCodigo);
    }
    
    @Override
    public List<SegRol> consultarRolesPorUsuario(Long usuCodigo) throws Exception{
    	return segRolLogic.consultarRolesPorUsuario(usuCodigo);
    }
    
    @Override
    public SegUsuario guardarUsuarioConRoles(String usuApellidos, String usuCodigoInterno,
			String usuConstrasena, String usuCorreo, String usuEstadoRegistro,
			Long usuIntentosFallidos, Long ciaCodigo_SegCompania, String usuLogin, String usuNombres,
			Date usuUltmimaModificacionPass, Long usuSession,
			List<String> rolesAsignados, String compania, String sistema) throws Exception{
    	
    	return segUsuarioLogic.guardarUsuarioConRoles(usuApellidos, usuCodigoInterno, usuConstrasena, usuCorreo, 
    			usuEstadoRegistro, usuIntentosFallidos, ciaCodigo_SegCompania, usuLogin, usuNombres, 
    			usuUltmimaModificacionPass, usuSession, rolesAsignados, compania, sistema);
    	
    }
    
    @Override
    public void modificarUsuarioConRoles(String usuApellidos, Long usuCodigo,
			String usuCodigoInterno, String usuConstrasena, String usuCorreo,
			String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania,
			String usuLogin, String usuNombres,
			Date usuUltmimaModificacionPass, Long usuSession,
			List<String> rolesAsignados, String compania, String sistema) throws Exception {
    	
    	segUsuarioLogic.modificarUsuarioConRoles(usuApellidos, usuCodigo, usuCodigoInterno, usuConstrasena, 
    			usuCorreo, usuEstadoRegistro, usuIntentosFallidos, ciaCodigo_SegCompania, usuLogin, usuNombres, 
    			usuUltmimaModificacionPass, usuSession, rolesAsignados, compania, sistema);
    	
    }
    
    @Override
    public SegUsuario consultarUsuarioPorLogin(String login)throws Exception{
    	return segUsuarioLogic.consultarUsuarioPorLogin(login);
    }
    
    @Override
    public List<SegUsuario> consultarUsuariosPorSistemaCompania(Long sisCodigfo, Long ciaCodigo) throws Exception {
    	return segUsuarioLogic.consultarUsuariosPorSistemaCompania(sisCodigfo, ciaCodigo);
    }
    
    @Override
    public void guardarPermiso (String perEstadoRegistro,
			Long sicCodigo_SegSistemaCia, Long gruCodigo_SegGrupoOpcion,
			Long opcCodigo_SegOpcion, Long rolCodigo_SegRol,
			Long sucCodigo_SegSucursal, Long usuCodigo_SegUsuario,
			Long usuCodigo_SegUsuario0) throws Exception {
    	segPermisoLogic.guardarPermiso(perEstadoRegistro, sicCodigo_SegSistemaCia, gruCodigo_SegGrupoOpcion, opcCodigo_SegOpcion, 
    			rolCodigo_SegRol, sucCodigo_SegSucursal, usuCodigo_SegUsuario, usuCodigo_SegUsuario0);
    }
    
    @Override
    public List<SegUsuario> consultarUsuariosPorRol(Long rolCodigo) throws Exception{
    	return segUsuarioLogic.consultarUsuariosPorRol(rolCodigo);
    }
    
    @Override
    public List<SegCompania> consultarCompaniasPorRol(Long rolCodigo) throws Exception{
    	return segCompaniaLogic.consultarCompaniasPorRol(rolCodigo);
    }
    
    @Override
    public List<SegOpcion> consultarPermisosDeOpcionesAsignadosARolesYUsuarios(Long codigoRol, Long codigoCompania, String codigoUsuario) throws Exception {
    	return segPermisoLogic.consultarPermisosDeOpcionesAsignadosARolesYUsuarios(codigoRol, codigoCompania, codigoUsuario);
    }
    
    @Override
    public List<SegGrupoOpcion> consultarGrupoOpcionesPorRol(Long rolCodigo)
			throws Exception{
    	return segGrupoOpcionLogic.consultarGrupoOpcionesPorRol(rolCodigo);
    }
    
    @Override
    public List<SegOpcion> consultarOpcionesDeGrupoOpcion(Long gruCodigo) throws Exception {
    	return segOpcionLogic.consultarOpcionesDeGrupoOpcion(gruCodigo);
    }
    
    @Override
    public List<SegPermiso> consultarPermisosDeOpcionesAsignadosARolesYUsuarios(Long rolCodigo,Long codigoCompania, Long codigoOpcion, String codigoUsuario) 
    		throws Exception{
    	return segPermisoLogic.consultarPermisosDeOpcionesAsignadosARolesYUsuarios(rolCodigo, codigoCompania, codigoOpcion, codigoUsuario);
    }
    
    @Override
    public void guardarPermisosParaRolOUsuarioANivelDeGruposUOpciones(Long rolCodigo,
			List<SegOpcion> listOpciones, Long codigoCompania,
			Long codigoSistema, String codigoUsuario) throws Exception{
    	segPermisoLogic.guardarPermisosParaRolOUsuarioANivelDeGruposUOpciones(rolCodigo, listOpciones, codigoCompania, codigoSistema, codigoUsuario);
    }
    
    @Override
    public SegSistema consultarSistemDeRol(Long rolCodigo) throws Exception{
    	return segSistemaLogic.consultarSistemDeRol(rolCodigo);
    }
    
    @Override
    public List<SegSistema> consultarSistemasALosQueTieneAccesoElUsuario(Long usuCodigo) throws Exception{
    	return segSistemaLogic.consultarSistemasALosQueTieneAccesoElUsuario(usuCodigo);
    }
    
    @Override
    public VinCamposParametrizables getVinCamposParametrizables(String llave) throws Exception {
    	VinCamposParametrizables vinCamposParametrizables = null;

    	try {
    		vinCamposParametrizables = vinCamposParametrizablesLogic.getVinCamposParametrizables(llave);
    	} catch (Exception e) {
    		throw e;
    	}

    	return vinCamposParametrizables;
    }
    //Metodo en el cual se consulta si el usuario es primera vez que logea y si lo es devuelve los datos
    public UsuarioDTO consultarPrimeraVez(String usu_login, String usu_password, String dominio) throws Exception{
    	
    	return segUsuarioLogic.consultarPrimeraVez( usu_login,  usu_password,  dominio); 
    }
    
    public boolean savePrimeraVez(UsuarioDTO usuario)throws Exception{
    	
    	return segUsuarioLogic.savePrimeraVez(usuario);
    }
    
    @Override
    public String generarNuevaContrasena(String usuLogin, String email) throws Exception{
    	return segUsuarioLogic.generarNuevaContrasena(usuLogin, email);
    }
    
    public void crearUsuario(SellPersonaDTO personaDTO)throws Exception{
    	segUsuarioLogic.crearUsuario(personaDTO);
    }
    
    public void agregarRolles(SellPersonaDTO personaDTO)throws Exception{
    	segUsuarioLogic.agregarRolles(personaDTO);
    }
    
	public List<VinCamposParametrizables> findByCriteriaInVinCamposParametrizables(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates)throws Exception{
		return vinCamposParametrizablesLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	@Override
	public long getSucuXPuntoVenta(String[] sucursalesSeleccionadas) throws Exception {
		// TODO GEMERAR METODO PUNTO DE VENTA CONTROL DE CAMBIO
		return 0;
	}
	
	@Override
	public void saveSegPermisoAllUser(boolean enCrm, boolean enVcloud, boolean enSellout,SellPersonaDTO personaDTO)throws Exception{
		segPermisoLogic.saveSegPermisoAllUser( enCrm, enVcloud, enSellout, personaDTO);
	}

	/**
	 * @author Andrés Felipe Vargas López
	 * @version Jun 18, 2018
	 *
	 * @see com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView#consultarRolPorSistemaCompania(java.lang.String, java.lang.String)
	 *
	 */
	@Override
	public List<SegRolDTO> consultarRolPorSistemaCompania(String sistema, String compania) throws Exception {
		return segRolLogic.consultarRolPorSistemaCompania(sistema, compania);
	}

	/**
	 * @author Andrés Felipe Vargas López
	 * @version Jun 18, 2018
	 *
	 * @see com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView#consultarRolPorUsuarioSistemaCompania(java.lang.String, java.lang.String, java.lang.String)
	 *
	 */
	@Override
	public List<SegRolDTO> consultarRolPorUsuarioSistemaCompania(String sistema, String compania, String login)
			throws Exception {
		return segRolLogic.consultarRolPorUsuarioSistemaCompania(sistema, compania, login);
	}

	/**
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 *
	 * @see com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView#crearUsuarioConRolesYPermisos(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.util.Date, java.lang.Long, java.util.List, java.lang.String, java.lang.String)
	 *
	 */
	@Override
	public void crearUsuarioConRolesYPermisos(String usuApellidos, String usuCodigoInterno, String usuConstrasena,
			String usuCorreo, String usuEstadoRegistro, Long usuIntentosFallidos, Long ciaCodigo_SegCompania,
			String usuLogin, String usuNombres, Date usuUltmimaModificacionPass, Long usuSession,
			List<SegRolDTO> rolesAsignados, String compania, String sistema) throws Exception {
		segUsuarioLogic.crearUsuarioConRolesYPermisos(usuApellidos, usuCodigoInterno, usuConstrasena, usuCorreo, usuEstadoRegistro, usuIntentosFallidos, ciaCodigo_SegCompania, usuLogin, usuNombres, usuUltmimaModificacionPass, usuSession, rolesAsignados, compania, sistema);
	}

	/**
	 * @author Andrés Felipe Vargas López
	 * @version Jun 19, 2018
	 *
	 * @see com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView#actualizarRolesYPermisosDeUsuario(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 *
	 */
	@Override
	public void actualizarRolesYPermisosDeUsuario(Long usuCodigo, Long rolCodigoActual, Long rolCodigoNuevo,
			Long sistema, Long compania) throws Exception {
		segUsuarioLogic.actualizarRolesYPermisosDeUsuario(usuCodigo, rolCodigoActual, rolCodigoNuevo, sistema, compania);
	}

	/**
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 *
	 * @see com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView#adicionarRolPermisoAUsuario(java.lang.Long, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long)
	 *
	 */
	@Override
	public void adicionarRolPermisoAUsuario(Long usuCodigo, List<SegRolDTO> listaRoles, Long sistema, Long compania,
			Long usuSession) throws Exception {
		segUsuarioLogic.adicionarRolPermisoAUsuario(usuCodigo, listaRoles, sistema, compania, usuSession);
		
	}

	/**
	 * @author Andrés Felipe Vargas López
	 * @version Jun 21, 2018
	 *
	 * @see com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView#eliminarRolPermisoAUsuario(java.lang.Long, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long)
	 *
	 */
	@Override
	public void eliminarRolPermisoAUsuario(Long usuCodigo, List<SegRolDTO> listaRoles, Long sistema, Long compania,
			Long usuSession) throws Exception {
		segUsuarioLogic.eliminarRolPermisoAUsuario(usuCodigo, listaRoles, sistema, compania, usuSession);
	}
    
}
