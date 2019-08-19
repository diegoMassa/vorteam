package com.vortexbird.sentinel.presentation.backingBeans;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegCambioPassDTO;
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
public class SegCambioPassView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegCambioPassView.class);
    private InputText txtCapEstado;
    private InputText txtCapToken;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtCapCodigo;
    private Calendar txtCapFechaFin;
    private Calendar txtCapFechaIni;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegCambioPassDTO> data;
    private SegCambioPassDTO selectedSegCambioPass;
    private SegCambioPass entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SegCambioPassView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegCambioPassDTO segCambioPassDTO = (SegCambioPassDTO) e.getObject();

            if (txtCapEstado == null) {
                txtCapEstado = new InputText();
            }

            txtCapEstado.setValue(segCambioPassDTO.getCapEstado());

            if (txtCapToken == null) {
                txtCapToken = new InputText();
            }

            txtCapToken.setValue(segCambioPassDTO.getCapToken());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segCambioPassDTO.getUsuCodigo_SegUsuario());

            if (txtCapCodigo == null) {
                txtCapCodigo = new InputText();
            }

            txtCapCodigo.setValue(segCambioPassDTO.getCapCodigo());

            if (txtCapFechaFin == null) {
                txtCapFechaFin = new Calendar();
            }

            txtCapFechaFin.setValue(segCambioPassDTO.getCapFechaFin());

            if (txtCapFechaIni == null) {
                txtCapFechaIni = new Calendar();
            }

            txtCapFechaIni.setValue(segCambioPassDTO.getCapFechaIni());

            Long capCodigo = FacesUtils.checkLong(txtCapCodigo);
            entity = businessDelegatorView.getSegCambioPass(capCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSegCambioPass = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSegCambioPass = null;

        if (txtCapEstado != null) {
            txtCapEstado.setValue(null);
            txtCapEstado.setDisabled(true);
        }

        if (txtCapToken != null) {
            txtCapToken.setValue(null);
            txtCapToken.setDisabled(true);
        }

        if (txtUsuCodigo_SegUsuario != null) {
            txtUsuCodigo_SegUsuario.setValue(null);
            txtUsuCodigo_SegUsuario.setDisabled(true);
        }

        if (txtCapFechaFin != null) {
            txtCapFechaFin.setValue(null);
            txtCapFechaFin.setDisabled(true);
        }

        if (txtCapFechaIni != null) {
            txtCapFechaIni.setValue(null);
            txtCapFechaIni.setDisabled(true);
        }

        if (txtCapCodigo != null) {
            txtCapCodigo.setValue(null);
            txtCapCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtCapFechaFin() {
        Date inputDate = (Date) txtCapFechaFin.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtCapFechaIni() {
        Date inputDate = (Date) txtCapFechaIni.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long capCodigo = FacesUtils.checkLong(txtCapCodigo);
            entity = (capCodigo != null)
                ? businessDelegatorView.getSegCambioPass(capCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCapEstado.setDisabled(false);
            txtCapToken.setDisabled(false);
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtCapFechaFin.setDisabled(false);
            txtCapFechaIni.setDisabled(false);
            txtCapCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCapEstado.setValue(entity.getCapEstado());
            txtCapEstado.setDisabled(false);
            txtCapFechaFin.setValue(entity.getCapFechaFin());
            txtCapFechaFin.setDisabled(false);
            txtCapFechaIni.setValue(entity.getCapFechaIni());
            txtCapFechaIni.setDisabled(false);
            txtCapToken.setValue(entity.getCapToken());
            txtCapToken.setDisabled(false);
            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
                                                   .getUsuCodigo());
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtCapCodigo.setValue(entity.getCapCodigo());
            txtCapCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSegCambioPass = (SegCambioPassDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedSegCambioPass"));
        txtCapEstado.setValue(selectedSegCambioPass.getCapEstado());
        txtCapEstado.setDisabled(false);
        txtCapFechaFin.setValue(selectedSegCambioPass.getCapFechaFin());
        txtCapFechaFin.setDisabled(false);
        txtCapFechaIni.setValue(selectedSegCambioPass.getCapFechaIni());
        txtCapFechaIni.setDisabled(false);
        txtCapToken.setValue(selectedSegCambioPass.getCapToken());
        txtCapToken.setDisabled(false);
        txtUsuCodigo_SegUsuario.setValue(selectedSegCambioPass.getUsuCodigo_SegUsuario());
        txtUsuCodigo_SegUsuario.setDisabled(false);
        txtCapCodigo.setValue(selectedSegCambioPass.getCapCodigo());
        txtCapCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSegCambioPass == null) && (entity == null)) {
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
            entity = new SegCambioPass();

            Long capCodigo = FacesUtils.checkLong(txtCapCodigo);

            entity.setCapCodigo(capCodigo);
            entity.setCapEstado(FacesUtils.checkString(txtCapEstado));
            entity.setCapFechaFin(FacesUtils.checkDate(txtCapFechaFin));
            entity.setCapFechaIni(FacesUtils.checkDate(txtCapFechaIni));
            entity.setCapToken(FacesUtils.checkString(txtCapToken));
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.saveSegCambioPass(entity);
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
                Long capCodigo = new Long(selectedSegCambioPass.getCapCodigo());
                entity = businessDelegatorView.getSegCambioPass(capCodigo);
            }

            entity.setCapEstado(FacesUtils.checkString(txtCapEstado));
            entity.setCapFechaFin(FacesUtils.checkDate(txtCapFechaFin));
            entity.setCapFechaIni(FacesUtils.checkDate(txtCapFechaIni));
            entity.setCapToken(FacesUtils.checkString(txtCapToken));
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.updateSegCambioPass(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSegCambioPass = (SegCambioPassDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedSegCambioPass"));

            Long capCodigo = new Long(selectedSegCambioPass.getCapCodigo());
            entity = businessDelegatorView.getSegCambioPass(capCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long capCodigo = FacesUtils.checkLong(txtCapCodigo);
            entity = businessDelegatorView.getSegCambioPass(capCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSegCambioPass(entity);
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
            selectedSegCambioPass = (SegCambioPassDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedSegCambioPass"));

            Long capCodigo = new Long(selectedSegCambioPass.getCapCodigo());
            entity = businessDelegatorView.getSegCambioPass(capCodigo);
            businessDelegatorView.deleteSegCambioPass(entity);
            data.remove(selectedSegCambioPass);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long capCodigo, String capEstado,
        Date capFechaFin, Date capFechaIni, String capToken,
        Long usuCodigo_SegUsuario) throws Exception {
        try {
            entity.setCapEstado(FacesUtils.checkString(capEstado));
            entity.setCapFechaFin(FacesUtils.checkDate(capFechaFin));
            entity.setCapFechaIni(FacesUtils.checkDate(capFechaIni));
            entity.setCapToken(FacesUtils.checkString(capToken));
            businessDelegatorView.updateSegCambioPass(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegCambioPassView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCapEstado() {
        return txtCapEstado;
    }

    public void setTxtCapEstado(InputText txtCapEstado) {
        this.txtCapEstado = txtCapEstado;
    }

    public InputText getTxtCapToken() {
        return txtCapToken;
    }

    public void setTxtCapToken(InputText txtCapToken) {
        this.txtCapToken = txtCapToken;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public Calendar getTxtCapFechaFin() {
        return txtCapFechaFin;
    }

    public void setTxtCapFechaFin(Calendar txtCapFechaFin) {
        this.txtCapFechaFin = txtCapFechaFin;
    }

    public Calendar getTxtCapFechaIni() {
        return txtCapFechaIni;
    }

    public void setTxtCapFechaIni(Calendar txtCapFechaIni) {
        this.txtCapFechaIni = txtCapFechaIni;
    }

    public InputText getTxtCapCodigo() {
        return txtCapCodigo;
    }

    public void setTxtCapCodigo(InputText txtCapCodigo) {
        this.txtCapCodigo = txtCapCodigo;
    }

    public List<SegCambioPassDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSegCambioPass();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SegCambioPassDTO> segCambioPassDTO) {
        this.data = segCambioPassDTO;
    }

    public SegCambioPassDTO getSelectedSegCambioPass() {
        return selectedSegCambioPass;
    }

    public void setSelectedSegCambioPass(SegCambioPassDTO segCambioPass) {
        this.selectedSegCambioPass = segCambioPass;
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
