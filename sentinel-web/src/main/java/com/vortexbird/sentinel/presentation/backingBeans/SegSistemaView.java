package com.vortexbird.sentinel.presentation.backingBeans;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegSistemaDTO;
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
public class SegSistemaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegSistemaView.class);
    private InputText txtSisDescripcion;
    private InputText txtSisEstadoRegistro;
    private InputText txtSisNombre;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtSisCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegSistemaDTO> data;
    private SegSistemaDTO selectedSegSistema;
    private SegSistema entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SegSistemaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegSistemaDTO segSistemaDTO = (SegSistemaDTO) e.getObject();

            if (txtSisDescripcion == null) {
                txtSisDescripcion = new InputText();
            }

            txtSisDescripcion.setValue(segSistemaDTO.getSisDescripcion());

            if (txtSisEstadoRegistro == null) {
                txtSisEstadoRegistro = new InputText();
            }

            txtSisEstadoRegistro.setValue(segSistemaDTO.getSisEstadoRegistro());

            if (txtSisNombre == null) {
                txtSisNombre = new InputText();
            }

            txtSisNombre.setValue(segSistemaDTO.getSisNombre());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segSistemaDTO.getUsuCodigo_SegUsuario());

            if (txtSisCodigo == null) {
                txtSisCodigo = new InputText();
            }

            txtSisCodigo.setValue(segSistemaDTO.getSisCodigo());

            Long sisCodigo = FacesUtils.checkLong(txtSisCodigo);
            entity = businessDelegatorView.getSegSistema(sisCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSegSistema = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSegSistema = null;

        if (txtSisDescripcion != null) {
            txtSisDescripcion.setValue(null);
            txtSisDescripcion.setDisabled(true);
        }

        if (txtSisEstadoRegistro != null) {
            txtSisEstadoRegistro.setValue(null);
            txtSisEstadoRegistro.setDisabled(true);
        }

        if (txtSisNombre != null) {
            txtSisNombre.setValue(null);
            txtSisNombre.setDisabled(true);
        }

        if (txtUsuCodigo_SegUsuario != null) {
            txtUsuCodigo_SegUsuario.setValue(null);
            txtUsuCodigo_SegUsuario.setDisabled(true);
        }

        if (txtSisCodigo != null) {
            txtSisCodigo.setValue(null);
            txtSisCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Long sisCodigo = FacesUtils.checkLong(txtSisCodigo);
            entity = (sisCodigo != null)
                ? businessDelegatorView.getSegSistema(sisCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtSisDescripcion.setDisabled(false);
            txtSisEstadoRegistro.setDisabled(false);
            txtSisNombre.setDisabled(false);
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtSisCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtSisDescripcion.setValue(entity.getSisDescripcion());
            txtSisDescripcion.setDisabled(false);
            txtSisEstadoRegistro.setValue(entity.getSisEstadoRegistro());
            txtSisEstadoRegistro.setDisabled(false);
            txtSisNombre.setValue(entity.getSisNombre());
            txtSisNombre.setDisabled(false);
            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
                                                   .getUsuCodigo());
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtSisCodigo.setValue(entity.getSisCodigo());
            txtSisCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSegSistema = (SegSistemaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedSegSistema"));
        txtSisDescripcion.setValue(selectedSegSistema.getSisDescripcion());
        txtSisDescripcion.setDisabled(false);
        txtSisEstadoRegistro.setValue(selectedSegSistema.getSisEstadoRegistro());
        txtSisEstadoRegistro.setDisabled(false);
        txtSisNombre.setValue(selectedSegSistema.getSisNombre());
        txtSisNombre.setDisabled(false);
        txtUsuCodigo_SegUsuario.setValue(selectedSegSistema.getUsuCodigo_SegUsuario());
        txtUsuCodigo_SegUsuario.setDisabled(false);
        txtSisCodigo.setValue(selectedSegSistema.getSisCodigo());
        txtSisCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSegSistema == null) && (entity == null)) {
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
            entity = new SegSistema();

            Long sisCodigo = FacesUtils.checkLong(txtSisCodigo);

            entity.setSisCodigo(sisCodigo);
            entity.setSisDescripcion(FacesUtils.checkString(txtSisDescripcion));
            entity.setSisEstadoRegistro(FacesUtils.checkString(
                    txtSisEstadoRegistro));
            entity.setSisNombre(FacesUtils.checkString(txtSisNombre));
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.saveSegSistema(entity);
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
                Long sisCodigo = new Long(selectedSegSistema.getSisCodigo());
                entity = businessDelegatorView.getSegSistema(sisCodigo);
            }

            entity.setSisDescripcion(FacesUtils.checkString(txtSisDescripcion));
            entity.setSisEstadoRegistro(FacesUtils.checkString(
                    txtSisEstadoRegistro));
            entity.setSisNombre(FacesUtils.checkString(txtSisNombre));
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.updateSegSistema(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSegSistema = (SegSistemaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedSegSistema"));

            Long sisCodigo = new Long(selectedSegSistema.getSisCodigo());
            entity = businessDelegatorView.getSegSistema(sisCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long sisCodigo = FacesUtils.checkLong(txtSisCodigo);
            entity = businessDelegatorView.getSegSistema(sisCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSegSistema(entity);
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
            selectedSegSistema = (SegSistemaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedSegSistema"));

            Long sisCodigo = new Long(selectedSegSistema.getSisCodigo());
            entity = businessDelegatorView.getSegSistema(sisCodigo);
            businessDelegatorView.deleteSegSistema(entity);
            data.remove(selectedSegSistema);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long sisCodigo, String sisDescripcion,
        String sisEstadoRegistro, String sisNombre, Long usuCodigo_SegUsuario)
        throws Exception {
        try {
            entity.setSisDescripcion(FacesUtils.checkString(sisDescripcion));
            entity.setSisEstadoRegistro(FacesUtils.checkString(
                    sisEstadoRegistro));
            entity.setSisNombre(FacesUtils.checkString(sisNombre));
            businessDelegatorView.updateSegSistema(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegSistemaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtSisDescripcion() {
        return txtSisDescripcion;
    }

    public void setTxtSisDescripcion(InputText txtSisDescripcion) {
        this.txtSisDescripcion = txtSisDescripcion;
    }

    public InputText getTxtSisEstadoRegistro() {
        return txtSisEstadoRegistro;
    }

    public void setTxtSisEstadoRegistro(InputText txtSisEstadoRegistro) {
        this.txtSisEstadoRegistro = txtSisEstadoRegistro;
    }

    public InputText getTxtSisNombre() {
        return txtSisNombre;
    }

    public void setTxtSisNombre(InputText txtSisNombre) {
        this.txtSisNombre = txtSisNombre;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public InputText getTxtSisCodigo() {
        return txtSisCodigo;
    }

    public void setTxtSisCodigo(InputText txtSisCodigo) {
        this.txtSisCodigo = txtSisCodigo;
    }

    public List<SegSistemaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSegSistema();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SegSistemaDTO> segSistemaDTO) {
        this.data = segSistemaDTO;
    }

    public SegSistemaDTO getSelectedSegSistema() {
        return selectedSegSistema;
    }

    public void setSelectedSegSistema(SegSistemaDTO segSistema) {
        this.selectedSegSistema = segSistema;
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
