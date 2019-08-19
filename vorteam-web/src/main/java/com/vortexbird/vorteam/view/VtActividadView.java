package com.vortexbird.vorteam.view;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.vorteam.domain.VtActividad;
import com.vortexbird.vorteam.dto.VtActividadDTO;
import com.vortexbird.vorteam.exception.ZMessManager;
import com.vortexbird.vorteam.utility.FacesUtils;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class VtActividadView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtActividadView.class);
    private InputText txtActivo;
    private InputText txtCasoSoporte;
    private InputText txtDescripcion;
    private InputText txtFacturable;
    private InputText txtHorasPresupuestadas;
    private InputText txtNombre;
    private InputText txtSprint;
    private InputText txtUsuaCreador;
    private InputText txtUsuaModificador;
    private InputText txtValorFacturable;
    private InputText txtEstaId_VtEstado;
    private InputText txtProyId_VtProyecto;
    private InputText txtTiacId_VtTipoActividad;
    private InputText txtActiId;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaLimite;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtActividadDTO> data;
    private VtActividadDTO selectedVtActividad;
    private VtActividad entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{businessDelegator}")
    private BusinessDelegator businessDelegatorView;

    public VtActividadView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedVtActividad = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtActividad = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtCasoSoporte != null) {
            txtCasoSoporte.setValue(null);
            txtCasoSoporte.setDisabled(true);
        }

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtFacturable != null) {
            txtFacturable.setValue(null);
            txtFacturable.setDisabled(true);
        }

        if (txtHorasPresupuestadas != null) {
            txtHorasPresupuestadas.setValue(null);
            txtHorasPresupuestadas.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtSprint != null) {
            txtSprint.setValue(null);
            txtSprint.setDisabled(true);
        }

        if (txtUsuaCreador != null) {
            txtUsuaCreador.setValue(null);
            txtUsuaCreador.setDisabled(true);
        }

        if (txtUsuaModificador != null) {
            txtUsuaModificador.setValue(null);
            txtUsuaModificador.setDisabled(true);
        }

        if (txtValorFacturable != null) {
            txtValorFacturable.setValue(null);
            txtValorFacturable.setDisabled(true);
        }

        if (txtEstaId_VtEstado != null) {
            txtEstaId_VtEstado.setValue(null);
            txtEstaId_VtEstado.setDisabled(true);
        }

        if (txtProyId_VtProyecto != null) {
            txtProyId_VtProyecto.setValue(null);
            txtProyId_VtProyecto.setDisabled(true);
        }

        if (txtTiacId_VtTipoActividad != null) {
            txtTiacId_VtTipoActividad.setValue(null);
            txtTiacId_VtTipoActividad.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaLimite != null) {
            txtFechaLimite.setValue(null);
            txtFechaLimite.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtActiId != null) {
            txtActiId.setValue(null);
            txtActiId.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaCreacion() {
        Date inputDate = (Date) txtFechaCreacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaLimite() {
        Date inputDate = (Date) txtFechaLimite.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaModificacion() {
        Date inputDate = (Date) txtFechaModificacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long actiId = FacesUtils.checkLong(txtActiId);
            entity = (actiId != null)
                ? businessDelegatorView.getVtActividad(actiId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtCasoSoporte.setDisabled(false);
            txtDescripcion.setDisabled(false);
            txtFacturable.setDisabled(false);
            txtHorasPresupuestadas.setDisabled(false);
            txtNombre.setDisabled(false);
            txtSprint.setDisabled(false);
            txtUsuaCreador.setDisabled(false);
            txtUsuaModificador.setDisabled(false);
            txtValorFacturable.setDisabled(false);
            txtEstaId_VtEstado.setDisabled(false);
            txtProyId_VtProyecto.setDisabled(false);
            txtTiacId_VtTipoActividad.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaLimite.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtActiId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtCasoSoporte.setValue(entity.getCasoSoporte());
            txtCasoSoporte.setDisabled(false);
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtFacturable.setValue(entity.getFacturable());
            txtFacturable.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaLimite.setValue(entity.getFechaLimite());
            txtFechaLimite.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtHorasPresupuestadas.setValue(entity.getHorasPresupuestadas());
            txtHorasPresupuestadas.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtSprint.setValue(entity.getSprint());
            txtSprint.setDisabled(false);
            txtUsuaCreador.setValue(entity.getUsuaCreador());
            txtUsuaCreador.setDisabled(false);
            txtUsuaModificador.setValue(entity.getUsuaModificador());
            txtUsuaModificador.setDisabled(false);
            txtValorFacturable.setValue(entity.getValorFacturable());
            txtValorFacturable.setDisabled(false);
            txtEstaId_VtEstado.setValue(entity.getVtEstado().getEstaId());
            txtEstaId_VtEstado.setDisabled(false);
            txtProyId_VtProyecto.setValue(entity.getVtProyecto().getProyId());
            txtProyId_VtProyecto.setDisabled(false);
            txtTiacId_VtTipoActividad.setValue(entity.getVtTipoActividad()
                                                     .getTiacId());
            txtTiacId_VtTipoActividad.setDisabled(false);
            txtActiId.setValue(entity.getActiId());
            txtActiId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtActividad = (VtActividadDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedVtActividad"));
        txtActivo.setValue(selectedVtActividad.getActivo());
        txtActivo.setDisabled(false);
        txtCasoSoporte.setValue(selectedVtActividad.getCasoSoporte());
        txtCasoSoporte.setDisabled(false);
        txtDescripcion.setValue(selectedVtActividad.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtFacturable.setValue(selectedVtActividad.getFacturable());
        txtFacturable.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtActividad.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaLimite.setValue(selectedVtActividad.getFechaLimite());
        txtFechaLimite.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtActividad.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtHorasPresupuestadas.setValue(selectedVtActividad.getHorasPresupuestadas());
        txtHorasPresupuestadas.setDisabled(false);
        txtNombre.setValue(selectedVtActividad.getNombre());
        txtNombre.setDisabled(false);
        txtSprint.setValue(selectedVtActividad.getSprint());
        txtSprint.setDisabled(false);
        txtUsuaCreador.setValue(selectedVtActividad.getUsuaCreador());
        txtUsuaCreador.setDisabled(false);
        txtUsuaModificador.setValue(selectedVtActividad.getUsuaModificador());
        txtUsuaModificador.setDisabled(false);
        txtValorFacturable.setValue(selectedVtActividad.getValorFacturable());
        txtValorFacturable.setDisabled(false);
        txtEstaId_VtEstado.setValue(selectedVtActividad.getEstaId_VtEstado());
        txtEstaId_VtEstado.setDisabled(false);
        txtProyId_VtProyecto.setValue(selectedVtActividad.getProyId_VtProyecto());
        txtProyId_VtProyecto.setDisabled(false);
        txtTiacId_VtTipoActividad.setValue(selectedVtActividad.getTiacId_VtTipoActividad());
        txtTiacId_VtTipoActividad.setDisabled(false);
        txtActiId.setValue(selectedVtActividad.getActiId());
        txtActiId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtActividad == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new VtActividad();

            Long actiId = FacesUtils.checkLong(txtActiId);

            entity.setActiId(actiId);
            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setCasoSoporte(FacesUtils.checkString(txtCasoSoporte));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFacturable(FacesUtils.checkString(txtFacturable));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaLimite(FacesUtils.checkDate(txtFechaLimite));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setHorasPresupuestadas(FacesUtils.checkBigDecimal(
                    txtHorasPresupuestadas));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setSprint(FacesUtils.checkString(txtSprint));
            entity.setUsuaCreador(FacesUtils.checkString(txtUsuaCreador));
            entity.setUsuaModificador(FacesUtils.checkString(txtUsuaModificador));
            entity.setValorFacturable(FacesUtils.checkLong(txtValorFacturable));
            entity.setVtEstado((FacesUtils.checkLong(txtEstaId_VtEstado) != null)
                ? businessDelegatorView.getVtEstado(FacesUtils.checkLong(
                        txtEstaId_VtEstado)) : null);
            entity.setVtProyecto((FacesUtils.checkLong(txtProyId_VtProyecto) != null)
                ? businessDelegatorView.getVtProyecto(FacesUtils.checkLong(
                        txtProyId_VtProyecto)) : null);
            entity.setVtTipoActividad((FacesUtils.checkLong(
                    txtTiacId_VtTipoActividad) != null)
                ? businessDelegatorView.getVtTipoActividad(FacesUtils.checkLong(
                        txtTiacId_VtTipoActividad)) : null);
            businessDelegatorView.saveVtActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long actiId = new Long(selectedVtActividad.getActiId());
                entity = businessDelegatorView.getVtActividad(actiId);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setCasoSoporte(FacesUtils.checkString(txtCasoSoporte));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFacturable(FacesUtils.checkString(txtFacturable));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaLimite(FacesUtils.checkDate(txtFechaLimite));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setHorasPresupuestadas(FacesUtils.checkBigDecimal(
                    txtHorasPresupuestadas));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setSprint(FacesUtils.checkString(txtSprint));
            entity.setUsuaCreador(FacesUtils.checkString(txtUsuaCreador));
            entity.setUsuaModificador(FacesUtils.checkString(txtUsuaModificador));
            entity.setValorFacturable(FacesUtils.checkLong(txtValorFacturable));
            entity.setVtEstado((FacesUtils.checkLong(txtEstaId_VtEstado) != null)
                ? businessDelegatorView.getVtEstado(FacesUtils.checkLong(
                        txtEstaId_VtEstado)) : null);
            entity.setVtProyecto((FacesUtils.checkLong(txtProyId_VtProyecto) != null)
                ? businessDelegatorView.getVtProyecto(FacesUtils.checkLong(
                        txtProyId_VtProyecto)) : null);
            entity.setVtTipoActividad((FacesUtils.checkLong(
                    txtTiacId_VtTipoActividad) != null)
                ? businessDelegatorView.getVtTipoActividad(FacesUtils.checkLong(
                        txtTiacId_VtTipoActividad)) : null);
            businessDelegatorView.updateVtActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtActividad = (VtActividadDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedVtActividad"));

            Long actiId = new Long(selectedVtActividad.getActiId());
            entity = businessDelegatorView.getVtActividad(actiId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long actiId = FacesUtils.checkLong(txtActiId);
            entity = businessDelegatorView.getVtActividad(actiId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String action_modifyWitDTO(Long actiId, String activo,
        String casoSoporte, String descripcion, String facturable,
        Date fechaCreacion, Date fechaLimite, Date fechaModificacion,
        Long horasPresupuestadas, String nombre, String sprint,
        String usuaCreador, String usuaModificador, Long valorFacturable,
        Long estaId_VtEstado, Long proyId_VtProyecto,
        Long tiacId_VtTipoActividad) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setCasoSoporte(FacesUtils.checkString(casoSoporte));
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setFacturable(FacesUtils.checkString(facturable));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaLimite(FacesUtils.checkDate(fechaLimite));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setHorasPresupuestadas(FacesUtils.checkBigDecimal(
                    horasPresupuestadas));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setSprint(FacesUtils.checkString(sprint));
            entity.setUsuaCreador(FacesUtils.checkString(usuaCreador));
            entity.setUsuaModificador(FacesUtils.checkString(usuaModificador));
            entity.setValorFacturable(FacesUtils.checkLong(valorFacturable));
            businessDelegatorView.updateVtActividad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtActividadView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtActivo() {
        return txtActivo;
    }

    public void setTxtActivo(InputText txtActivo) {
        this.txtActivo = txtActivo;
    }

    public InputText getTxtCasoSoporte() {
        return txtCasoSoporte;
    }

    public void setTxtCasoSoporte(InputText txtCasoSoporte) {
        this.txtCasoSoporte = txtCasoSoporte;
    }

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtFacturable() {
        return txtFacturable;
    }

    public void setTxtFacturable(InputText txtFacturable) {
        this.txtFacturable = txtFacturable;
    }

    public InputText getTxtHorasPresupuestadas() {
        return txtHorasPresupuestadas;
    }

    public void setTxtHorasPresupuestadas(InputText txtHorasPresupuestadas) {
        this.txtHorasPresupuestadas = txtHorasPresupuestadas;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtSprint() {
        return txtSprint;
    }

    public void setTxtSprint(InputText txtSprint) {
        this.txtSprint = txtSprint;
    }

    public InputText getTxtUsuaCreador() {
        return txtUsuaCreador;
    }

    public void setTxtUsuaCreador(InputText txtUsuaCreador) {
        this.txtUsuaCreador = txtUsuaCreador;
    }

    public InputText getTxtUsuaModificador() {
        return txtUsuaModificador;
    }

    public void setTxtUsuaModificador(InputText txtUsuaModificador) {
        this.txtUsuaModificador = txtUsuaModificador;
    }

    public InputText getTxtValorFacturable() {
        return txtValorFacturable;
    }

    public void setTxtValorFacturable(InputText txtValorFacturable) {
        this.txtValorFacturable = txtValorFacturable;
    }

    public InputText getTxtEstaId_VtEstado() {
        return txtEstaId_VtEstado;
    }

    public void setTxtEstaId_VtEstado(InputText txtEstaId_VtEstado) {
        this.txtEstaId_VtEstado = txtEstaId_VtEstado;
    }

    public InputText getTxtProyId_VtProyecto() {
        return txtProyId_VtProyecto;
    }

    public void setTxtProyId_VtProyecto(InputText txtProyId_VtProyecto) {
        this.txtProyId_VtProyecto = txtProyId_VtProyecto;
    }

    public InputText getTxtTiacId_VtTipoActividad() {
        return txtTiacId_VtTipoActividad;
    }

    public void setTxtTiacId_VtTipoActividad(
        InputText txtTiacId_VtTipoActividad) {
        this.txtTiacId_VtTipoActividad = txtTiacId_VtTipoActividad;
    }

    public Calendar getTxtFechaCreacion() {
        return txtFechaCreacion;
    }

    public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
        this.txtFechaCreacion = txtFechaCreacion;
    }

    public Calendar getTxtFechaLimite() {
        return txtFechaLimite;
    }

    public void setTxtFechaLimite(Calendar txtFechaLimite) {
        this.txtFechaLimite = txtFechaLimite;
    }

    public Calendar getTxtFechaModificacion() {
        return txtFechaModificacion;
    }

    public void setTxtFechaModificacion(Calendar txtFechaModificacion) {
        this.txtFechaModificacion = txtFechaModificacion;
    }

    public InputText getTxtActiId() {
        return txtActiId;
    }

    public void setTxtActiId(InputText txtActiId) {
        this.txtActiId = txtActiId;
    }

    public List<VtActividadDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVtActividad();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VtActividadDTO> vtActividadDTO) {
        this.data = vtActividadDTO;
    }

    public VtActividadDTO getSelectedVtActividad() {
        return selectedVtActividad;
    }

    public void setSelectedVtActividad(VtActividadDTO vtActividad) {
        this.selectedVtActividad = vtActividad;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public BusinessDelegator getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        BusinessDelegator businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
