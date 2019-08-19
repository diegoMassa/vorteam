package com.vortexbird.sentinel.presentation.backingBeans;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegSucursalDTO;
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
public class SegSucursalView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegSucursalView.class);
    private InputText txtSucCodigoInterno;
    private InputText txtSucEstadoRegistro;
    private InputText txtSucNombre;
    private InputText txtCiaCodigo_SegCompania;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtSucCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegSucursalDTO> data;
    private SegSucursalDTO selectedSegSucursal;
    private SegSucursal entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SegSucursalView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegSucursalDTO segSucursalDTO = (SegSucursalDTO) e.getObject();

            if (txtSucCodigoInterno == null) {
                txtSucCodigoInterno = new InputText();
            }

            txtSucCodigoInterno.setValue(segSucursalDTO.getSucCodigoInterno());

            if (txtSucEstadoRegistro == null) {
                txtSucEstadoRegistro = new InputText();
            }

            txtSucEstadoRegistro.setValue(segSucursalDTO.getSucEstadoRegistro());

            if (txtSucNombre == null) {
                txtSucNombre = new InputText();
            }

            txtSucNombre.setValue(segSucursalDTO.getSucNombre());

            if (txtCiaCodigo_SegCompania == null) {
                txtCiaCodigo_SegCompania = new InputText();
            }

            txtCiaCodigo_SegCompania.setValue(segSucursalDTO.getCiaCodigo_SegCompania());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segSucursalDTO.getUsuCodigo_SegUsuario());

            if (txtSucCodigo == null) {
                txtSucCodigo = new InputText();
            }

            txtSucCodigo.setValue(segSucursalDTO.getSucCodigo());

            Long sucCodigo = FacesUtils.checkLong(txtSucCodigo);
            entity = businessDelegatorView.getSegSucursal(sucCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSegSucursal = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSegSucursal = null;

        if (txtSucCodigoInterno != null) {
            txtSucCodigoInterno.setValue(null);
            txtSucCodigoInterno.setDisabled(true);
        }

        if (txtSucEstadoRegistro != null) {
            txtSucEstadoRegistro.setValue(null);
            txtSucEstadoRegistro.setDisabled(true);
        }

        if (txtSucNombre != null) {
            txtSucNombre.setValue(null);
            txtSucNombre.setDisabled(true);
        }

        if (txtCiaCodigo_SegCompania != null) {
            txtCiaCodigo_SegCompania.setValue(null);
            txtCiaCodigo_SegCompania.setDisabled(true);
        }

        if (txtUsuCodigo_SegUsuario != null) {
            txtUsuCodigo_SegUsuario.setValue(null);
            txtUsuCodigo_SegUsuario.setDisabled(true);
        }

        if (txtSucCodigo != null) {
            txtSucCodigo.setValue(null);
            txtSucCodigo.setDisabled(false);
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
            Long sucCodigo = FacesUtils.checkLong(txtSucCodigo);
            entity = (sucCodigo != null)
                ? businessDelegatorView.getSegSucursal(sucCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtSucCodigoInterno.setDisabled(false);
            txtSucEstadoRegistro.setDisabled(false);
            txtSucNombre.setDisabled(false);
            txtCiaCodigo_SegCompania.setDisabled(false);
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtSucCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtSucCodigoInterno.setValue(entity.getSucCodigoInterno());
            txtSucCodigoInterno.setDisabled(false);
            txtSucEstadoRegistro.setValue(entity.getSucEstadoRegistro());
            txtSucEstadoRegistro.setDisabled(false);
            txtSucNombre.setValue(entity.getSucNombre());
            txtSucNombre.setDisabled(false);
            txtCiaCodigo_SegCompania.setValue(entity.getSegCompania()
                                                    .getCiaCodigo());
            txtCiaCodigo_SegCompania.setDisabled(false);
            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
                                                   .getUsuCodigo());
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtSucCodigo.setValue(entity.getSucCodigo());
            txtSucCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSegSucursal = (SegSucursalDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedSegSucursal"));
        txtSucCodigoInterno.setValue(selectedSegSucursal.getSucCodigoInterno());
        txtSucCodigoInterno.setDisabled(false);
        txtSucEstadoRegistro.setValue(selectedSegSucursal.getSucEstadoRegistro());
        txtSucEstadoRegistro.setDisabled(false);
        txtSucNombre.setValue(selectedSegSucursal.getSucNombre());
        txtSucNombre.setDisabled(false);
        txtCiaCodigo_SegCompania.setValue(selectedSegSucursal.getCiaCodigo_SegCompania());
        txtCiaCodigo_SegCompania.setDisabled(false);
        txtUsuCodigo_SegUsuario.setValue(selectedSegSucursal.getUsuCodigo_SegUsuario());
        txtUsuCodigo_SegUsuario.setDisabled(false);
        txtSucCodigo.setValue(selectedSegSucursal.getSucCodigo());
        txtSucCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSegSucursal == null) && (entity == null)) {
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
            entity = new SegSucursal();

            Long sucCodigo = FacesUtils.checkLong(txtSucCodigo);

            entity.setSucCodigo(sucCodigo);
            entity.setSucCodigoInterno(FacesUtils.checkString(
                    txtSucCodigoInterno));
            entity.setSucEstadoRegistro(FacesUtils.checkString(
                    txtSucEstadoRegistro));
            entity.setSucNombre(FacesUtils.checkString(txtSucNombre));
            entity.setSegCompania((FacesUtils.checkLong(
                    txtCiaCodigo_SegCompania) != null)
                ? businessDelegatorView.getSegCompania(FacesUtils.checkLong(
                        txtCiaCodigo_SegCompania)) : null);
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.saveSegSucursal(entity);
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
                Long sucCodigo = new Long(selectedSegSucursal.getSucCodigo());
                entity = businessDelegatorView.getSegSucursal(sucCodigo);
            }

            entity.setSucCodigoInterno(FacesUtils.checkString(
                    txtSucCodigoInterno));
            entity.setSucEstadoRegistro(FacesUtils.checkString(
                    txtSucEstadoRegistro));
            entity.setSucNombre(FacesUtils.checkString(txtSucNombre));
            entity.setSegCompania((FacesUtils.checkLong(
                    txtCiaCodigo_SegCompania) != null)
                ? businessDelegatorView.getSegCompania(FacesUtils.checkLong(
                        txtCiaCodigo_SegCompania)) : null);
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.updateSegSucursal(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSegSucursal = (SegSucursalDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedSegSucursal"));

            Long sucCodigo = new Long(selectedSegSucursal.getSucCodigo());
            entity = businessDelegatorView.getSegSucursal(sucCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long sucCodigo = FacesUtils.checkLong(txtSucCodigo);
            entity = businessDelegatorView.getSegSucursal(sucCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSegSucursal(entity);
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
            selectedSegSucursal = (SegSucursalDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedSegSucursal"));

            Long sucCodigo = new Long(selectedSegSucursal.getSucCodigo());
            entity = businessDelegatorView.getSegSucursal(sucCodigo);
            businessDelegatorView.deleteSegSucursal(entity);
            data.remove(selectedSegSucursal);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long sucCodigo, String sucCodigoInterno,
        String sucEstadoRegistro, String sucNombre, Long ciaCodigo_SegCompania,
        Long usuCodigo_SegUsuario) throws Exception {
        try {
            entity.setSucCodigoInterno(FacesUtils.checkString(sucCodigoInterno));
            entity.setSucEstadoRegistro(FacesUtils.checkString(
                    sucEstadoRegistro));
            entity.setSucNombre(FacesUtils.checkString(sucNombre));
            businessDelegatorView.updateSegSucursal(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegSucursalView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtSucCodigoInterno() {
        return txtSucCodigoInterno;
    }

    public void setTxtSucCodigoInterno(InputText txtSucCodigoInterno) {
        this.txtSucCodigoInterno = txtSucCodigoInterno;
    }

    public InputText getTxtSucEstadoRegistro() {
        return txtSucEstadoRegistro;
    }

    public void setTxtSucEstadoRegistro(InputText txtSucEstadoRegistro) {
        this.txtSucEstadoRegistro = txtSucEstadoRegistro;
    }

    public InputText getTxtSucNombre() {
        return txtSucNombre;
    }

    public void setTxtSucNombre(InputText txtSucNombre) {
        this.txtSucNombre = txtSucNombre;
    }

    public InputText getTxtCiaCodigo_SegCompania() {
        return txtCiaCodigo_SegCompania;
    }

    public void setTxtCiaCodigo_SegCompania(InputText txtCiaCodigo_SegCompania) {
        this.txtCiaCodigo_SegCompania = txtCiaCodigo_SegCompania;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public InputText getTxtSucCodigo() {
        return txtSucCodigo;
    }

    public void setTxtSucCodigo(InputText txtSucCodigo) {
        this.txtSucCodigo = txtSucCodigo;
    }

    public List<SegSucursalDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSegSucursal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SegSucursalDTO> segSucursalDTO) {
        this.data = segSucursalDTO;
    }

    public SegSucursalDTO getSelectedSegSucursal() {
        return selectedSegSucursal;
    }

    public void setSelectedSegSucursal(SegSucursalDTO segSucursal) {
        this.selectedSegSucursal = segSucursal;
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
