package com.vortexbird.sentinel.presentation.backingBeans;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegRolUsuarioDTO;
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
public class SegRolUsuarioView implements Serializable {
	
//    private static final long serialVersionUID = 1L;
//    private static final Logger log = LoggerFactory.getLogger(SegRolUsuarioView.class);
//    private InputText txtRluEstadoRegistro;
//    private InputText txtRolCodigo_SegRol;
//    private InputText txtUsuCodigo_SegUsuario;
//    private InputText txtRluCodigo;
//    private CommandButton btnSave;
//    private CommandButton btnModify;
//    private CommandButton btnDelete;
//    private CommandButton btnClear;
//    private List<SegRolUsuarioDTO> data;
//    private SegRolUsuarioDTO selectedSegRolUsuario;
//    private SegRolUsuario entity;
//    private boolean showDialog;
//    @ManagedProperty(value = "#{BusinessDelegatorView}")
//    private IBusinessDelegatorView businessDelegatorView;
//
//    public SegRolUsuarioView() {
//        super();
//    }
//
//    public void rowEventListener(RowEditEvent e) {
//        try {
//            SegRolUsuarioDTO segRolUsuarioDTO = (SegRolUsuarioDTO) e.getObject();
//
//            if (txtRluEstadoRegistro == null) {
//                txtRluEstadoRegistro = new InputText();
//            }
//
//            txtRluEstadoRegistro.setValue(segRolUsuarioDTO.getRluEstadoRegistro());
//
//            if (txtRolCodigo_SegRol == null) {
//                txtRolCodigo_SegRol = new InputText();
//            }
//
//            txtRolCodigo_SegRol.setValue(segRolUsuarioDTO.getRolCodigo_SegRol());
//
//            if (txtUsuCodigo_SegUsuario == null) {
//                txtUsuCodigo_SegUsuario = new InputText();
//            }
//
//            txtUsuCodigo_SegUsuario.setValue(segRolUsuarioDTO.getUsuCodigo_SegUsuario());
//
//            if (txtRluCodigo == null) {
//                txtRluCodigo = new InputText();
//            }
//
//            txtRluCodigo.setValue(segRolUsuarioDTO.getRluCodigo());
//
//            Long rluCodigo = FacesUtils.checkLong(txtRluCodigo);
//            entity = businessDelegatorView.getSegRolUsuario(rluCodigo);
//
//            action_modify();
//        } catch (Exception ex) {
//        }
//    }
//
//    public String action_new() {
//        action_clear();
//        selectedSegRolUsuario = null;
//        setShowDialog(true);
//
//        return "";
//    }
//
//    public String action_clear() {
//        entity = null;
//        selectedSegRolUsuario = null;
//
//        if (txtRluEstadoRegistro != null) {
//            txtRluEstadoRegistro.setValue(null);
//            txtRluEstadoRegistro.setDisabled(true);
//        }
//
//        if (txtRolCodigo_SegRol != null) {
//            txtRolCodigo_SegRol.setValue(null);
//            txtRolCodigo_SegRol.setDisabled(true);
//        }
//
//        if (txtUsuCodigo_SegUsuario != null) {
//            txtUsuCodigo_SegUsuario.setValue(null);
//            txtUsuCodigo_SegUsuario.setDisabled(true);
//        }
//
//        if (txtRluCodigo != null) {
//            txtRluCodigo.setValue(null);
//            txtRluCodigo.setDisabled(false);
//        }
//
//        if (btnSave != null) {
//            btnSave.setDisabled(true);
//        }
//
//        if (btnDelete != null) {
//            btnDelete.setDisabled(true);
//        }
//
//        return "";
//    }
//
//    public void listener_txtId() {
//        try {
//            Long rluCodigo = FacesUtils.checkLong(txtRluCodigo);
//            entity = (rluCodigo != null)
//                ? businessDelegatorView.getSegRolUsuario(rluCodigo) : null;
//        } catch (Exception e) {
//            entity = null;
//        }
//
//        if (entity == null) {
//            txtRluEstadoRegistro.setDisabled(false);
//            txtRolCodigo_SegRol.setDisabled(false);
//            txtUsuCodigo_SegUsuario.setDisabled(false);
//            txtRluCodigo.setDisabled(false);
//            btnSave.setDisabled(false);
//        } else {
//            txtRluEstadoRegistro.setValue(entity.getRluEstadoRegistro());
//            txtRluEstadoRegistro.setDisabled(false);
//            txtRolCodigo_SegRol.setValue(entity.getSegRol().getRolCodigo());
//            txtRolCodigo_SegRol.setDisabled(false);
//            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
//                                                   .getUsuCodigo());
//            txtUsuCodigo_SegUsuario.setDisabled(false);
//            txtRluCodigo.setValue(entity.getRluCodigo());
//            txtRluCodigo.setDisabled(true);
//            btnSave.setDisabled(false);
//
//            if (btnDelete != null) {
//                btnDelete.setDisabled(false);
//            }
//        }
//    }
//
//    public String action_edit(ActionEvent evt) {
//        selectedSegRolUsuario = (SegRolUsuarioDTO) (evt.getComponent()
//                                                       .getAttributes()
//                                                       .get("selectedSegRolUsuario"));
//        txtRluEstadoRegistro.setValue(selectedSegRolUsuario.getRluEstadoRegistro());
//        txtRluEstadoRegistro.setDisabled(false);
//        txtRolCodigo_SegRol.setValue(selectedSegRolUsuario.getRolCodigo_SegRol());
//        txtRolCodigo_SegRol.setDisabled(false);
//        txtUsuCodigo_SegUsuario.setValue(selectedSegRolUsuario.getUsuCodigo_SegUsuario());
//        txtUsuCodigo_SegUsuario.setDisabled(false);
//        txtRluCodigo.setValue(selectedSegRolUsuario.getRluCodigo());
//        txtRluCodigo.setDisabled(true);
//        btnSave.setDisabled(false);
//        setShowDialog(true);
//
//        return "";
//    }
//
//    public String action_save() {
//        try {
//            if ((selectedSegRolUsuario == null) && (entity == null)) {
//                action_create();
//            } else {
//                action_modify();
//            }
//
//            data = null;
//        } catch (Exception e) {
//            FacesUtils.addErrorMessage(e.getMessage());
//        }
//
//        return "";
//    }
//
//    public String action_create() {
//        try {
//            entity = new SegRolUsuario();
//
//            Long rluCodigo = FacesUtils.checkLong(txtRluCodigo);
//
//            entity.setRluCodigo(rluCodigo);
//            entity.setRluEstadoRegistro(FacesUtils.checkString(
//                    txtRluEstadoRegistro));
//            entity.setSegRol((FacesUtils.checkLong(txtRolCodigo_SegRol) != null)
//                ? businessDelegatorView.getSegRol(FacesUtils.checkLong(
//                        txtRolCodigo_SegRol)) : null);
//            entity.setSegUsuarioByModUsuCodigo((FacesUtils.checkLong(
//                    txtUsuCodigo_SegUsuario) != null)
//                ? businessDelegatorView.getSegUsuarioByModUsuCodigo(
//                    FacesUtils.checkLong(txtUsuCodigo_SegUsuario)) : null);
//            entity.setSegUsuarioBySegUsuarioUsuCodigo((FacesUtils.checkLong(
//                    txtUsuCodigo_SegUsuario) != null)
//                ? businessDelegatorView.getSegUsuarioBySegUsuarioUsuCodigo(
//                    FacesUtils.checkLong(txtUsuCodigo_SegUsuario)) : null);
//            businessDelegatorView.saveSegRolUsuario(entity);
//            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
//            action_clear();
//        } catch (Exception e) {
//            entity = null;
//            FacesUtils.addErrorMessage(e.getMessage());
//        }
//
//        return "";
//    }
//
//    public String action_modify() {
//        try {
//            if (entity == null) {
//                Long rluCodigo = new Long(selectedSegRolUsuario.getRluCodigo());
//                entity = businessDelegatorView.getSegRolUsuario(rluCodigo);
//            }
//
//            entity.setRluEstadoRegistro(FacesUtils.checkString(
//                    txtRluEstadoRegistro));
//            entity.setSegRol((FacesUtils.checkLong(txtRolCodigo_SegRol) != null)
//                ? businessDelegatorView.getSegRol(FacesUtils.checkLong(
//                        txtRolCodigo_SegRol)) : null);
//            entity.setSegUsuarioByModUsuCodigo((FacesUtils.checkLong(
//                    txtUsuCodigo_SegUsuario) != null)
//                ? businessDelegatorView.getSegUsuarioByModUsuCodigo(
//                    FacesUtils.checkLong(txtUsuCodigo_SegUsuario)) : null);
//            entity.setSegUsuarioBySegUsuarioUsuCodigo((FacesUtils.checkLong(
//                    txtUsuCodigo_SegUsuario) != null)
//                ? businessDelegatorView.getSegUsuarioBySegUsuarioUsuCodigo(
//                    FacesUtils.checkLong(txtUsuCodigo_SegUsuario)) : null);
//            businessDelegatorView.updateSegRolUsuario(entity);
//            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
//        } catch (Exception e) {
//            data = null;
//            FacesUtils.addErrorMessage(e.getMessage());
//        }
//
//        return "";
//    }
//
//    public String action_delete_datatable(ActionEvent evt) {
//        try {
//            selectedSegRolUsuario = (SegRolUsuarioDTO) (evt.getComponent()
//                                                           .getAttributes()
//                                                           .get("selectedSegRolUsuario"));
//
//            Long rluCodigo = new Long(selectedSegRolUsuario.getRluCodigo());
//            entity = businessDelegatorView.getSegRolUsuario(rluCodigo);
//            action_delete();
//        } catch (Exception e) {
//            FacesUtils.addErrorMessage(e.getMessage());
//        }
//
//        return "";
//    }
//
//    public String action_delete_master() {
//        try {
//            Long rluCodigo = FacesUtils.checkLong(txtRluCodigo);
//            entity = businessDelegatorView.getSegRolUsuario(rluCodigo);
//            action_delete();
//        } catch (Exception e) {
//            FacesUtils.addErrorMessage(e.getMessage());
//        }
//
//        return "";
//    }
//
//    public void action_delete() throws Exception {
//        try {
//            businessDelegatorView.deleteSegRolUsuario(entity);
//            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
//            action_clear();
//            data = null;
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    public String action_closeDialog() {
//        setShowDialog(false);
//        action_clear();
//
//        return "";
//    }
//
//    public String actionDeleteDataTableEditable(ActionEvent evt) {
//        try {
//            selectedSegRolUsuario = (SegRolUsuarioDTO) (evt.getComponent()
//                                                           .getAttributes()
//                                                           .get("selectedSegRolUsuario"));
//
//            Long rluCodigo = new Long(selectedSegRolUsuario.getRluCodigo());
//            entity = businessDelegatorView.getSegRolUsuario(rluCodigo);
//            businessDelegatorView.deleteSegRolUsuario(entity);
//            data.remove(selectedSegRolUsuario);
//            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
//            action_clear();
//        } catch (Exception e) {
//            FacesUtils.addErrorMessage(e.getMessage());
//        }
//
//        return "";
//    }
//
//    public String action_modifyWitDTO(Long rluCodigo, String rluEstadoRegistro,
//        Long rolCodigo_SegRol, Long usuCodigo_SegUsuario,
//        Long usuCodigo_SegUsuario0) throws Exception {
//        try {
//            entity.setRluEstadoRegistro(FacesUtils.checkString(
//                    rluEstadoRegistro));
//            businessDelegatorView.updateSegRolUsuario(entity);
//            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
//        } catch (Exception e) {
//            //renderManager.getOnDemandRenderer("SegRolUsuarioView").requestRender();
//            FacesUtils.addErrorMessage(e.getMessage());
//            throw e;
//        }
//
//        return "";
//    }
//
//    public InputText getTxtRluEstadoRegistro() {
//        return txtRluEstadoRegistro;
//    }
//
//    public void setTxtRluEstadoRegistro(InputText txtRluEstadoRegistro) {
//        this.txtRluEstadoRegistro = txtRluEstadoRegistro;
//    }
//
//    public InputText getTxtRolCodigo_SegRol() {
//        return txtRolCodigo_SegRol;
//    }
//
//    public void setTxtRolCodigo_SegRol(InputText txtRolCodigo_SegRol) {
//        this.txtRolCodigo_SegRol = txtRolCodigo_SegRol;
//    }
//
//    public InputText getTxtUsuCodigo_SegUsuario() {
//        return txtUsuCodigo_SegUsuario;
//    }
//
//    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
//        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
//    }
//
//    public InputText getTxtRluCodigo() {
//        return txtRluCodigo;
//    }
//
//    public void setTxtRluCodigo(InputText txtRluCodigo) {
//        this.txtRluCodigo = txtRluCodigo;
//    }
//
//    public List<SegRolUsuarioDTO> getData() {
//        try {
//            if (data == null) {
//                data = businessDelegatorView.getDataSegRolUsuario();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return data;
//    }
//
//    public void setData(List<SegRolUsuarioDTO> segRolUsuarioDTO) {
//        this.data = segRolUsuarioDTO;
//    }
//
//    public SegRolUsuarioDTO getSelectedSegRolUsuario() {
//        return selectedSegRolUsuario;
//    }
//
//    public void setSelectedSegRolUsuario(SegRolUsuarioDTO segRolUsuario) {
//        this.selectedSegRolUsuario = segRolUsuario;
//    }
//
//    public CommandButton getBtnSave() {
//        return btnSave;
//    }
//
//    public void setBtnSave(CommandButton btnSave) {
//        this.btnSave = btnSave;
//    }
//
//    public CommandButton getBtnModify() {
//        return btnModify;
//    }
//
//    public void setBtnModify(CommandButton btnModify) {
//        this.btnModify = btnModify;
//    }
//
//    public CommandButton getBtnDelete() {
//        return btnDelete;
//    }
//
//    public void setBtnDelete(CommandButton btnDelete) {
//        this.btnDelete = btnDelete;
//    }
//
//    public CommandButton getBtnClear() {
//        return btnClear;
//    }
//
//    public void setBtnClear(CommandButton btnClear) {
//        this.btnClear = btnClear;
//    }
//
//    public TimeZone getTimeZone() {
//        return java.util.TimeZone.getDefault();
//    }
//
//    public IBusinessDelegatorView getBusinessDelegatorView() {
//        return businessDelegatorView;
//    }
//
//    public void setBusinessDelegatorView(
//        IBusinessDelegatorView businessDelegatorView) {
//        this.businessDelegatorView = businessDelegatorView;
//    }
//
//    public boolean isShowDialog() {
//        return showDialog;
//    }
//
//    public void setShowDialog(boolean showDialog) {
//        this.showDialog = showDialog;
//    }
}
