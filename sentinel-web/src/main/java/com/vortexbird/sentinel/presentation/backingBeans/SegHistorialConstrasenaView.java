package com.vortexbird.sentinel.presentation.backingBeans;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegHistorialConstrasenaDTO;
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
public class SegHistorialConstrasenaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegHistorialConstrasenaView.class);
    private InputText txtHcoConstrasena;
    private InputText txtHcoDetalleCambio;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtHcoCodigo;
    private Calendar txtHcoFechaCambio;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegHistorialConstrasenaDTO> data;
    private SegHistorialConstrasenaDTO selectedSegHistorialConstrasena;
    private SegHistorialConstrasena entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SegHistorialConstrasenaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegHistorialConstrasenaDTO segHistorialConstrasenaDTO = (SegHistorialConstrasenaDTO) e.getObject();

            if (txtHcoConstrasena == null) {
                txtHcoConstrasena = new InputText();
            }

            txtHcoConstrasena.setValue(segHistorialConstrasenaDTO.getHcoConstrasena());

            if (txtHcoDetalleCambio == null) {
                txtHcoDetalleCambio = new InputText();
            }

            txtHcoDetalleCambio.setValue(segHistorialConstrasenaDTO.getHcoDetalleCambio());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segHistorialConstrasenaDTO.getUsuCodigo_SegUsuario());

            if (txtHcoCodigo == null) {
                txtHcoCodigo = new InputText();
            }

            txtHcoCodigo.setValue(segHistorialConstrasenaDTO.getHcoCodigo());

            if (txtHcoFechaCambio == null) {
                txtHcoFechaCambio = new Calendar();
            }

            txtHcoFechaCambio.setValue(segHistorialConstrasenaDTO.getHcoFechaCambio());

            Long hcoCodigo = FacesUtils.checkLong(txtHcoCodigo);
            entity = businessDelegatorView.getSegHistorialConstrasena(hcoCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSegHistorialConstrasena = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSegHistorialConstrasena = null;

        if (txtHcoConstrasena != null) {
            txtHcoConstrasena.setValue(null);
            txtHcoConstrasena.setDisabled(true);
        }

        if (txtHcoDetalleCambio != null) {
            txtHcoDetalleCambio.setValue(null);
            txtHcoDetalleCambio.setDisabled(true);
        }

        if (txtUsuCodigo_SegUsuario != null) {
            txtUsuCodigo_SegUsuario.setValue(null);
            txtUsuCodigo_SegUsuario.setDisabled(true);
        }

        if (txtHcoFechaCambio != null) {
            txtHcoFechaCambio.setValue(null);
            txtHcoFechaCambio.setDisabled(true);
        }

        if (txtHcoCodigo != null) {
            txtHcoCodigo.setValue(null);
            txtHcoCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtHcoFechaCambio() {
        Date inputDate = (Date) txtHcoFechaCambio.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long hcoCodigo = FacesUtils.checkLong(txtHcoCodigo);
            entity = (hcoCodigo != null)
                ? businessDelegatorView.getSegHistorialConstrasena(hcoCodigo)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtHcoConstrasena.setDisabled(false);
            txtHcoDetalleCambio.setDisabled(false);
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtHcoFechaCambio.setDisabled(false);
            txtHcoCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtHcoConstrasena.setValue(entity.getHcoConstrasena());
            txtHcoConstrasena.setDisabled(false);
            txtHcoDetalleCambio.setValue(entity.getHcoDetalleCambio());
            txtHcoDetalleCambio.setDisabled(false);
            txtHcoFechaCambio.setValue(entity.getHcoFechaCambio());
            txtHcoFechaCambio.setDisabled(false);
            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
                                                   .getUsuCodigo());
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtHcoCodigo.setValue(entity.getHcoCodigo());
            txtHcoCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSegHistorialConstrasena = (SegHistorialConstrasenaDTO) (evt.getComponent()
                                                                           .getAttributes()
                                                                           .get("selectedSegHistorialConstrasena"));
        txtHcoConstrasena.setValue(selectedSegHistorialConstrasena.getHcoConstrasena());
        txtHcoConstrasena.setDisabled(false);
        txtHcoDetalleCambio.setValue(selectedSegHistorialConstrasena.getHcoDetalleCambio());
        txtHcoDetalleCambio.setDisabled(false);
        txtHcoFechaCambio.setValue(selectedSegHistorialConstrasena.getHcoFechaCambio());
        txtHcoFechaCambio.setDisabled(false);
        txtUsuCodigo_SegUsuario.setValue(selectedSegHistorialConstrasena.getUsuCodigo_SegUsuario());
        txtUsuCodigo_SegUsuario.setDisabled(false);
        txtHcoCodigo.setValue(selectedSegHistorialConstrasena.getHcoCodigo());
        txtHcoCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSegHistorialConstrasena == null) && (entity == null)) {
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
            entity = new SegHistorialConstrasena();

            Long hcoCodigo = FacesUtils.checkLong(txtHcoCodigo);

            entity.setHcoCodigo(hcoCodigo);
            entity.setHcoConstrasena(FacesUtils.checkString(txtHcoConstrasena));
            entity.setHcoDetalleCambio(FacesUtils.checkString(
                    txtHcoDetalleCambio));
            entity.setHcoFechaCambio(FacesUtils.checkDate(txtHcoFechaCambio));
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.saveSegHistorialConstrasena(entity);
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
                Long hcoCodigo = new Long(selectedSegHistorialConstrasena.getHcoCodigo());
                entity = businessDelegatorView.getSegHistorialConstrasena(hcoCodigo);
            }

            entity.setHcoConstrasena(FacesUtils.checkString(txtHcoConstrasena));
            entity.setHcoDetalleCambio(FacesUtils.checkString(
                    txtHcoDetalleCambio));
            entity.setHcoFechaCambio(FacesUtils.checkDate(txtHcoFechaCambio));
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.updateSegHistorialConstrasena(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSegHistorialConstrasena = (SegHistorialConstrasenaDTO) (evt.getComponent()
                                                                               .getAttributes()
                                                                               .get("selectedSegHistorialConstrasena"));

            Long hcoCodigo = new Long(selectedSegHistorialConstrasena.getHcoCodigo());
            entity = businessDelegatorView.getSegHistorialConstrasena(hcoCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long hcoCodigo = FacesUtils.checkLong(txtHcoCodigo);
            entity = businessDelegatorView.getSegHistorialConstrasena(hcoCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSegHistorialConstrasena(entity);
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
            selectedSegHistorialConstrasena = (SegHistorialConstrasenaDTO) (evt.getComponent()
                                                                               .getAttributes()
                                                                               .get("selectedSegHistorialConstrasena"));

            Long hcoCodigo = new Long(selectedSegHistorialConstrasena.getHcoCodigo());
            entity = businessDelegatorView.getSegHistorialConstrasena(hcoCodigo);
            businessDelegatorView.deleteSegHistorialConstrasena(entity);
            data.remove(selectedSegHistorialConstrasena);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long hcoCodigo, String hcoConstrasena,
        String hcoDetalleCambio, Date hcoFechaCambio, Long usuCodigo_SegUsuario)
        throws Exception {
        try {
            entity.setHcoConstrasena(FacesUtils.checkString(hcoConstrasena));
            entity.setHcoDetalleCambio(FacesUtils.checkString(hcoDetalleCambio));
            entity.setHcoFechaCambio(FacesUtils.checkDate(hcoFechaCambio));
            businessDelegatorView.updateSegHistorialConstrasena(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegHistorialConstrasenaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtHcoConstrasena() {
        return txtHcoConstrasena;
    }

    public void setTxtHcoConstrasena(InputText txtHcoConstrasena) {
        this.txtHcoConstrasena = txtHcoConstrasena;
    }

    public InputText getTxtHcoDetalleCambio() {
        return txtHcoDetalleCambio;
    }

    public void setTxtHcoDetalleCambio(InputText txtHcoDetalleCambio) {
        this.txtHcoDetalleCambio = txtHcoDetalleCambio;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public Calendar getTxtHcoFechaCambio() {
        return txtHcoFechaCambio;
    }

    public void setTxtHcoFechaCambio(Calendar txtHcoFechaCambio) {
        this.txtHcoFechaCambio = txtHcoFechaCambio;
    }

    public InputText getTxtHcoCodigo() {
        return txtHcoCodigo;
    }

    public void setTxtHcoCodigo(InputText txtHcoCodigo) {
        this.txtHcoCodigo = txtHcoCodigo;
    }

    public List<SegHistorialConstrasenaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSegHistorialConstrasena();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(
        List<SegHistorialConstrasenaDTO> segHistorialConstrasenaDTO) {
        this.data = segHistorialConstrasenaDTO;
    }

    public SegHistorialConstrasenaDTO getSelectedSegHistorialConstrasena() {
        return selectedSegHistorialConstrasena;
    }

    public void setSelectedSegHistorialConstrasena(
        SegHistorialConstrasenaDTO segHistorialConstrasena) {
        this.selectedSegHistorialConstrasena = segHistorialConstrasena;
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
