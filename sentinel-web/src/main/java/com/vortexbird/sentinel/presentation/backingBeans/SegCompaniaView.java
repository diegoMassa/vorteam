package com.vortexbird.sentinel.presentation.backingBeans;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegCompaniaDTO;
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
public class SegCompaniaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegCompaniaView.class);
    private InputText txtCiaCodigoInterno;
    private InputText txtCiaEstadoRegistro;
    private InputText txtCiaNombre;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtCiaCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegCompaniaDTO> data;
    private SegCompaniaDTO selectedSegCompania;
    private SegCompania entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SegCompaniaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegCompaniaDTO segCompaniaDTO = (SegCompaniaDTO) e.getObject();

            if (txtCiaCodigoInterno == null) {
                txtCiaCodigoInterno = new InputText();
            }

            txtCiaCodigoInterno.setValue(segCompaniaDTO.getCiaCodigoInterno());

            if (txtCiaEstadoRegistro == null) {
                txtCiaEstadoRegistro = new InputText();
            }

            txtCiaEstadoRegistro.setValue(segCompaniaDTO.getCiaEstadoRegistro());

            if (txtCiaNombre == null) {
                txtCiaNombre = new InputText();
            }

            txtCiaNombre.setValue(segCompaniaDTO.getCiaNombre());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segCompaniaDTO.getUsuCodigo_SegUsuario());

            if (txtCiaCodigo == null) {
                txtCiaCodigo = new InputText();
            }

            txtCiaCodigo.setValue(segCompaniaDTO.getCiaCodigo());

            Long ciaCodigo = FacesUtils.checkLong(txtCiaCodigo);
            entity = businessDelegatorView.getSegCompania(ciaCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSegCompania = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSegCompania = null;

        if (txtCiaCodigoInterno != null) {
            txtCiaCodigoInterno.setValue(null);
            txtCiaCodigoInterno.setDisabled(true);
        }

        if (txtCiaEstadoRegistro != null) {
            txtCiaEstadoRegistro.setValue(null);
            txtCiaEstadoRegistro.setDisabled(true);
        }

        if (txtCiaNombre != null) {
            txtCiaNombre.setValue(null);
            txtCiaNombre.setDisabled(true);
        }

        if (txtUsuCodigo_SegUsuario != null) {
            txtUsuCodigo_SegUsuario.setValue(null);
            txtUsuCodigo_SegUsuario.setDisabled(true);
        }

        if (txtCiaCodigo != null) {
            txtCiaCodigo.setValue(null);
            txtCiaCodigo.setDisabled(false);
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
            Long ciaCodigo = FacesUtils.checkLong(txtCiaCodigo);
            entity = (ciaCodigo != null)
                ? businessDelegatorView.getSegCompania(ciaCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCiaCodigoInterno.setDisabled(false);
            txtCiaEstadoRegistro.setDisabled(false);
            txtCiaNombre.setDisabled(false);
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtCiaCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCiaCodigoInterno.setValue(entity.getCiaCodigoInterno());
            txtCiaCodigoInterno.setDisabled(false);
            txtCiaEstadoRegistro.setValue(entity.getCiaEstadoRegistro());
            txtCiaEstadoRegistro.setDisabled(false);
            txtCiaNombre.setValue(entity.getCiaNombre());
            txtCiaNombre.setDisabled(false);
            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
                                                   .getUsuCodigo());
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtCiaCodigo.setValue(entity.getCiaCodigo());
            txtCiaCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSegCompania = (SegCompaniaDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedSegCompania"));
        txtCiaCodigoInterno.setValue(selectedSegCompania.getCiaCodigoInterno());
        txtCiaCodigoInterno.setDisabled(false);
        txtCiaEstadoRegistro.setValue(selectedSegCompania.getCiaEstadoRegistro());
        txtCiaEstadoRegistro.setDisabled(false);
        txtCiaNombre.setValue(selectedSegCompania.getCiaNombre());
        txtCiaNombre.setDisabled(false);
        txtUsuCodigo_SegUsuario.setValue(selectedSegCompania.getUsuCodigo_SegUsuario());
        txtUsuCodigo_SegUsuario.setDisabled(false);
        txtCiaCodigo.setValue(selectedSegCompania.getCiaCodigo());
        txtCiaCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSegCompania == null) && (entity == null)) {
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
            entity = new SegCompania();

            Long ciaCodigo = FacesUtils.checkLong(txtCiaCodigo);

            entity.setCiaCodigo(ciaCodigo);
            entity.setCiaCodigoInterno(FacesUtils.checkString(
                    txtCiaCodigoInterno));
            entity.setCiaEstadoRegistro(FacesUtils.checkString(
                    txtCiaEstadoRegistro));
            entity.setCiaNombre(FacesUtils.checkString(txtCiaNombre));
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.saveSegCompania(entity);
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
                Long ciaCodigo = new Long(selectedSegCompania.getCiaCodigo());
                entity = businessDelegatorView.getSegCompania(ciaCodigo);
            }

            entity.setCiaCodigoInterno(FacesUtils.checkString(
                    txtCiaCodigoInterno));
            entity.setCiaEstadoRegistro(FacesUtils.checkString(
                    txtCiaEstadoRegistro));
            entity.setCiaNombre(FacesUtils.checkString(txtCiaNombre));
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.updateSegCompania(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSegCompania = (SegCompaniaDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedSegCompania"));

            Long ciaCodigo = new Long(selectedSegCompania.getCiaCodigo());
            entity = businessDelegatorView.getSegCompania(ciaCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long ciaCodigo = FacesUtils.checkLong(txtCiaCodigo);
            entity = businessDelegatorView.getSegCompania(ciaCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSegCompania(entity);
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
            selectedSegCompania = (SegCompaniaDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedSegCompania"));

            Long ciaCodigo = new Long(selectedSegCompania.getCiaCodigo());
            entity = businessDelegatorView.getSegCompania(ciaCodigo);
            businessDelegatorView.deleteSegCompania(entity);
            data.remove(selectedSegCompania);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long ciaCodigo, String ciaCodigoInterno,
        String ciaEstadoRegistro, String ciaNombre, Long usuCodigo_SegUsuario)
        throws Exception {
        try {
            entity.setCiaCodigoInterno(FacesUtils.checkString(ciaCodigoInterno));
            entity.setCiaEstadoRegistro(FacesUtils.checkString(
                    ciaEstadoRegistro));
            entity.setCiaNombre(FacesUtils.checkString(ciaNombre));
            businessDelegatorView.updateSegCompania(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegCompaniaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCiaCodigoInterno() {
        return txtCiaCodigoInterno;
    }

    public void setTxtCiaCodigoInterno(InputText txtCiaCodigoInterno) {
        this.txtCiaCodigoInterno = txtCiaCodigoInterno;
    }

    public InputText getTxtCiaEstadoRegistro() {
        return txtCiaEstadoRegistro;
    }

    public void setTxtCiaEstadoRegistro(InputText txtCiaEstadoRegistro) {
        this.txtCiaEstadoRegistro = txtCiaEstadoRegistro;
    }

    public InputText getTxtCiaNombre() {
        return txtCiaNombre;
    }

    public void setTxtCiaNombre(InputText txtCiaNombre) {
        this.txtCiaNombre = txtCiaNombre;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public InputText getTxtCiaCodigo() {
        return txtCiaCodigo;
    }

    public void setTxtCiaCodigo(InputText txtCiaCodigo) {
        this.txtCiaCodigo = txtCiaCodigo;
    }

    public List<SegCompaniaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSegCompania();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SegCompaniaDTO> segCompaniaDTO) {
        this.data = segCompaniaDTO;
    }

    public SegCompaniaDTO getSelectedSegCompania() {
        return selectedSegCompania;
    }

    public void setSelectedSegCompania(SegCompaniaDTO segCompania) {
        this.selectedSegCompania = segCompania;
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
