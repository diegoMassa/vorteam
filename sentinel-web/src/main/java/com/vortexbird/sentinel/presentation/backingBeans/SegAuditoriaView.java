package com.vortexbird.sentinel.presentation.backingBeans;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegAuditoriaDTO;
import com.vortexbird.sentinel.presentation.businessDelegate.*;
import com.vortexbird.sentinel.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class SegAuditoriaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegAuditoriaView.class);
    private InputText txtCampo;
    private InputText txtCodRegistro;
    private InputText txtTabla;
    private InputText txtTipo;
    private InputText txtValorAnterior;
    private InputText txtValorNuevo;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtAutCodigo;
    private Calendar txtFecha;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegAuditoriaDTO> data;
    private SegAuditoriaDTO selectedSegAuditoria;
    private SegAuditoria entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SegAuditoriaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegAuditoriaDTO segAuditoriaDTO = (SegAuditoriaDTO) e.getObject();

            if (txtCampo == null) {
                txtCampo = new InputText();
            }

            txtCampo.setValue(segAuditoriaDTO.getCampo());

            if (txtCodRegistro == null) {
                txtCodRegistro = new InputText();
            }

            txtCodRegistro.setValue(segAuditoriaDTO.getCodRegistro());

            if (txtTabla == null) {
                txtTabla = new InputText();
            }

            txtTabla.setValue(segAuditoriaDTO.getTabla());

            if (txtTipo == null) {
                txtTipo = new InputText();
            }

            txtTipo.setValue(segAuditoriaDTO.getTipo());

            if (txtValorAnterior == null) {
                txtValorAnterior = new InputText();
            }

            txtValorAnterior.setValue(segAuditoriaDTO.getValorAnterior());

            if (txtValorNuevo == null) {
                txtValorNuevo = new InputText();
            }

            txtValorNuevo.setValue(segAuditoriaDTO.getValorNuevo());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segAuditoriaDTO.getUsuCodigo_SegUsuario());

            if (txtAutCodigo == null) {
                txtAutCodigo = new InputText();
            }

            txtAutCodigo.setValue(segAuditoriaDTO.getAutCodigo());

            if (txtFecha == null) {
                txtFecha = new Calendar();
            }

            txtFecha.setValue(segAuditoriaDTO.getFecha());

            Long autCodigo = FacesUtils.checkLong(txtAutCodigo);
            entity = businessDelegatorView.getSegAuditoria(autCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSegAuditoria = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSegAuditoria = null;

        if (txtCampo != null) {
            txtCampo.setValue(null);
            txtCampo.setDisabled(true);
        }

        if (txtCodRegistro != null) {
            txtCodRegistro.setValue(null);
            txtCodRegistro.setDisabled(true);
        }

        if (txtTabla != null) {
            txtTabla.setValue(null);
            txtTabla.setDisabled(true);
        }

        if (txtTipo != null) {
            txtTipo.setValue(null);
            txtTipo.setDisabled(true);
        }

        if (txtValorAnterior != null) {
            txtValorAnterior.setValue(null);
            txtValorAnterior.setDisabled(true);
        }

        if (txtValorNuevo != null) {
            txtValorNuevo.setValue(null);
            txtValorNuevo.setDisabled(true);
        }

        if (txtUsuCodigo_SegUsuario != null) {
            txtUsuCodigo_SegUsuario.setValue(null);
            txtUsuCodigo_SegUsuario.setDisabled(true);
        }

        if (txtFecha != null) {
            txtFecha.setValue(null);
            txtFecha.setDisabled(true);
        }

        if (txtAutCodigo != null) {
            txtAutCodigo.setValue(null);
            txtAutCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFecha() {
        Date inputDate = (Date) txtFecha.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long autCodigo = FacesUtils.checkLong(txtAutCodigo);
            entity = (autCodigo != null)
                ? businessDelegatorView.getSegAuditoria(autCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCampo.setDisabled(false);
            txtCodRegistro.setDisabled(false);
            txtTabla.setDisabled(false);
            txtTipo.setDisabled(false);
            txtValorAnterior.setDisabled(false);
            txtValorNuevo.setDisabled(false);
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtFecha.setDisabled(false);
            txtAutCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCampo.setValue(entity.getCampo());
            txtCampo.setDisabled(false);
            txtCodRegistro.setValue(entity.getCodRegistro());
            txtCodRegistro.setDisabled(false);
            txtFecha.setValue(entity.getFecha());
            txtFecha.setDisabled(false);
            txtTabla.setValue(entity.getTabla());
            txtTabla.setDisabled(false);
            txtTipo.setValue(entity.getTipo());
            txtTipo.setDisabled(false);
            txtValorAnterior.setValue(entity.getValorAnterior());
            txtValorAnterior.setDisabled(false);
            txtValorNuevo.setValue(entity.getValorNuevo());
            txtValorNuevo.setDisabled(false);
            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
                                                   .getUsuCodigo());
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtAutCodigo.setValue(entity.getAutCodigo());
            txtAutCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSegAuditoria = (SegAuditoriaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedSegAuditoria"));
        txtCampo.setValue(selectedSegAuditoria.getCampo());
        txtCampo.setDisabled(false);
        txtCodRegistro.setValue(selectedSegAuditoria.getCodRegistro());
        txtCodRegistro.setDisabled(false);
        txtFecha.setValue(selectedSegAuditoria.getFecha());
        txtFecha.setDisabled(false);
        txtTabla.setValue(selectedSegAuditoria.getTabla());
        txtTabla.setDisabled(false);
        txtTipo.setValue(selectedSegAuditoria.getTipo());
        txtTipo.setDisabled(false);
        txtValorAnterior.setValue(selectedSegAuditoria.getValorAnterior());
        txtValorAnterior.setDisabled(false);
        txtValorNuevo.setValue(selectedSegAuditoria.getValorNuevo());
        txtValorNuevo.setDisabled(false);
        txtUsuCodigo_SegUsuario.setValue(selectedSegAuditoria.getUsuCodigo_SegUsuario());
        txtUsuCodigo_SegUsuario.setDisabled(false);
        txtAutCodigo.setValue(selectedSegAuditoria.getAutCodigo());
        txtAutCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSegAuditoria == null) && (entity == null)) {
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
            entity = new SegAuditoria();

            Long autCodigo = FacesUtils.checkLong(txtAutCodigo);

            entity.setAutCodigo(autCodigo);
            entity.setCampo(FacesUtils.checkString(txtCampo));
            entity.setCodRegistro(FacesUtils.checkLong(txtCodRegistro));
            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setTabla(FacesUtils.checkString(txtTabla));
            entity.setTipo(FacesUtils.checkString(txtTipo));
            entity.setValorAnterior(FacesUtils.checkString(txtValorAnterior));
            entity.setValorNuevo(FacesUtils.checkString(txtValorNuevo));
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.saveSegAuditoria(entity);
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
                Long autCodigo = new Long(selectedSegAuditoria.getAutCodigo());
                entity = businessDelegatorView.getSegAuditoria(autCodigo);
            }

            entity.setCampo(FacesUtils.checkString(txtCampo));
            entity.setCodRegistro(FacesUtils.checkLong(txtCodRegistro));
            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setTabla(FacesUtils.checkString(txtTabla));
            entity.setTipo(FacesUtils.checkString(txtTipo));
            entity.setValorAnterior(FacesUtils.checkString(txtValorAnterior));
            entity.setValorNuevo(FacesUtils.checkString(txtValorNuevo));
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.updateSegAuditoria(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSegAuditoria = (SegAuditoriaDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedSegAuditoria"));

            Long autCodigo = new Long(selectedSegAuditoria.getAutCodigo());
            entity = businessDelegatorView.getSegAuditoria(autCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long autCodigo = FacesUtils.checkLong(txtAutCodigo);
            entity = businessDelegatorView.getSegAuditoria(autCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSegAuditoria(entity);
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

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedSegAuditoria = (SegAuditoriaDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedSegAuditoria"));

            Long autCodigo = new Long(selectedSegAuditoria.getAutCodigo());
            entity = businessDelegatorView.getSegAuditoria(autCodigo);
            businessDelegatorView.deleteSegAuditoria(entity);
            data.remove(selectedSegAuditoria);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long autCodigo, String campo,
        Long codRegistro, Date fecha, String tabla, String tipo,
        String valorAnterior, String valorNuevo, Long usuCodigo_SegUsuario)
        throws Exception {
        try {
            entity.setCampo(FacesUtils.checkString(campo));
            entity.setCodRegistro(FacesUtils.checkLong(codRegistro));
            entity.setFecha(FacesUtils.checkDate(fecha));
            entity.setTabla(FacesUtils.checkString(tabla));
            entity.setTipo(FacesUtils.checkString(tipo));
            entity.setValorAnterior(FacesUtils.checkString(valorAnterior));
            entity.setValorNuevo(FacesUtils.checkString(valorNuevo));
            businessDelegatorView.updateSegAuditoria(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegAuditoriaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCampo() {
        return txtCampo;
    }

    public void setTxtCampo(InputText txtCampo) {
        this.txtCampo = txtCampo;
    }

    public InputText getTxtCodRegistro() {
        return txtCodRegistro;
    }

    public void setTxtCodRegistro(InputText txtCodRegistro) {
        this.txtCodRegistro = txtCodRegistro;
    }

    public InputText getTxtTabla() {
        return txtTabla;
    }

    public void setTxtTabla(InputText txtTabla) {
        this.txtTabla = txtTabla;
    }

    public InputText getTxtTipo() {
        return txtTipo;
    }

    public void setTxtTipo(InputText txtTipo) {
        this.txtTipo = txtTipo;
    }

    public InputText getTxtValorAnterior() {
        return txtValorAnterior;
    }

    public void setTxtValorAnterior(InputText txtValorAnterior) {
        this.txtValorAnterior = txtValorAnterior;
    }

    public InputText getTxtValorNuevo() {
        return txtValorNuevo;
    }

    public void setTxtValorNuevo(InputText txtValorNuevo) {
        this.txtValorNuevo = txtValorNuevo;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public Calendar getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(Calendar txtFecha) {
        this.txtFecha = txtFecha;
    }

    public InputText getTxtAutCodigo() {
        return txtAutCodigo;
    }

    public void setTxtAutCodigo(InputText txtAutCodigo) {
        this.txtAutCodigo = txtAutCodigo;
    }

    public List<SegAuditoriaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSegAuditoria();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SegAuditoriaDTO> segAuditoriaDTO) {
        this.data = segAuditoriaDTO;
    }

    public SegAuditoriaDTO getSelectedSegAuditoria() {
        return selectedSegAuditoria;
    }

    public void setSelectedSegAuditoria(SegAuditoriaDTO segAuditoria) {
        this.selectedSegAuditoria = segAuditoria;
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

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
