package com.vortexbird.sentinel.presentation.backingBeans;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegSistemaCiaDTO;
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
public class SegSistemaCiaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegSistemaCiaView.class);
    private InputText txtSicEstadoRegistro;
    private InputText txtCiaCodigo_SegCompania;
    private InputText txtSisCodigo_SegSistema;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtSicCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegSistemaCiaDTO> data;
    private SegSistemaCiaDTO selectedSegSistemaCia;
    private SegSistemaCia entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SegSistemaCiaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegSistemaCiaDTO segSistemaCiaDTO = (SegSistemaCiaDTO) e.getObject();

            if (txtSicEstadoRegistro == null) {
                txtSicEstadoRegistro = new InputText();
            }

            txtSicEstadoRegistro.setValue(segSistemaCiaDTO.getSicEstadoRegistro());

            if (txtCiaCodigo_SegCompania == null) {
                txtCiaCodigo_SegCompania = new InputText();
            }

            txtCiaCodigo_SegCompania.setValue(segSistemaCiaDTO.getCiaCodigo_SegCompania());

            if (txtSisCodigo_SegSistema == null) {
                txtSisCodigo_SegSistema = new InputText();
            }

            txtSisCodigo_SegSistema.setValue(segSistemaCiaDTO.getSisCodigo_SegSistema());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segSistemaCiaDTO.getUsuCodigo_SegUsuario());

            if (txtSicCodigo == null) {
                txtSicCodigo = new InputText();
            }

            txtSicCodigo.setValue(segSistemaCiaDTO.getSicCodigo());

            Long sicCodigo = FacesUtils.checkLong(txtSicCodigo);
            entity = businessDelegatorView.getSegSistemaCia(sicCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSegSistemaCia = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSegSistemaCia = null;

        if (txtSicEstadoRegistro != null) {
            txtSicEstadoRegistro.setValue(null);
            txtSicEstadoRegistro.setDisabled(true);
        }

        if (txtCiaCodigo_SegCompania != null) {
            txtCiaCodigo_SegCompania.setValue(null);
            txtCiaCodigo_SegCompania.setDisabled(true);
        }

        if (txtSisCodigo_SegSistema != null) {
            txtSisCodigo_SegSistema.setValue(null);
            txtSisCodigo_SegSistema.setDisabled(true);
        }

        if (txtUsuCodigo_SegUsuario != null) {
            txtUsuCodigo_SegUsuario.setValue(null);
            txtUsuCodigo_SegUsuario.setDisabled(true);
        }

        if (txtSicCodigo != null) {
            txtSicCodigo.setValue(null);
            txtSicCodigo.setDisabled(false);
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
            Long sicCodigo = FacesUtils.checkLong(txtSicCodigo);
            entity = (sicCodigo != null)
                ? businessDelegatorView.getSegSistemaCia(sicCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtSicEstadoRegistro.setDisabled(false);
            txtCiaCodigo_SegCompania.setDisabled(false);
            txtSisCodigo_SegSistema.setDisabled(false);
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtSicCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtSicEstadoRegistro.setValue(entity.getSicEstadoRegistro());
            txtSicEstadoRegistro.setDisabled(false);
            txtCiaCodigo_SegCompania.setValue(entity.getSegCompania()
                                                    .getCiaCodigo());
            txtCiaCodigo_SegCompania.setDisabled(false);
            txtSisCodigo_SegSistema.setValue(entity.getSegSistema()
                                                   .getSisCodigo());
            txtSisCodigo_SegSistema.setDisabled(false);
            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
                                                   .getUsuCodigo());
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtSicCodigo.setValue(entity.getSicCodigo());
            txtSicCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSegSistemaCia = (SegSistemaCiaDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedSegSistemaCia"));
        txtSicEstadoRegistro.setValue(selectedSegSistemaCia.getSicEstadoRegistro());
        txtSicEstadoRegistro.setDisabled(false);
        txtCiaCodigo_SegCompania.setValue(selectedSegSistemaCia.getCiaCodigo_SegCompania());
        txtCiaCodigo_SegCompania.setDisabled(false);
        txtSisCodigo_SegSistema.setValue(selectedSegSistemaCia.getSisCodigo_SegSistema());
        txtSisCodigo_SegSistema.setDisabled(false);
        txtUsuCodigo_SegUsuario.setValue(selectedSegSistemaCia.getUsuCodigo_SegUsuario());
        txtUsuCodigo_SegUsuario.setDisabled(false);
        txtSicCodigo.setValue(selectedSegSistemaCia.getSicCodigo());
        txtSicCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSegSistemaCia == null) && (entity == null)) {
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
            entity = new SegSistemaCia();

            Long sicCodigo = FacesUtils.checkLong(txtSicCodigo);

            entity.setSicCodigo(sicCodigo);
            entity.setSicEstadoRegistro(FacesUtils.checkString(
                    txtSicEstadoRegistro));
            entity.setSegCompania((FacesUtils.checkLong(
                    txtCiaCodigo_SegCompania) != null)
                ? businessDelegatorView.getSegCompania(FacesUtils.checkLong(
                        txtCiaCodigo_SegCompania)) : null);
            entity.setSegSistema((FacesUtils.checkLong(txtSisCodigo_SegSistema) != null)
                ? businessDelegatorView.getSegSistema(FacesUtils.checkLong(
                        txtSisCodigo_SegSistema)) : null);
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.saveSegSistemaCia(entity);
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
                Long sicCodigo = new Long(selectedSegSistemaCia.getSicCodigo());
                entity = businessDelegatorView.getSegSistemaCia(sicCodigo);
            }

            entity.setSicEstadoRegistro(FacesUtils.checkString(
                    txtSicEstadoRegistro));
            entity.setSegCompania((FacesUtils.checkLong(
                    txtCiaCodigo_SegCompania) != null)
                ? businessDelegatorView.getSegCompania(FacesUtils.checkLong(
                        txtCiaCodigo_SegCompania)) : null);
            entity.setSegSistema((FacesUtils.checkLong(txtSisCodigo_SegSistema) != null)
                ? businessDelegatorView.getSegSistema(FacesUtils.checkLong(
                        txtSisCodigo_SegSistema)) : null);
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.updateSegSistemaCia(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSegSistemaCia = (SegSistemaCiaDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedSegSistemaCia"));

            Long sicCodigo = new Long(selectedSegSistemaCia.getSicCodigo());
            entity = businessDelegatorView.getSegSistemaCia(sicCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long sicCodigo = FacesUtils.checkLong(txtSicCodigo);
            entity = businessDelegatorView.getSegSistemaCia(sicCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSegSistemaCia(entity);
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
            selectedSegSistemaCia = (SegSistemaCiaDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedSegSistemaCia"));

            Long sicCodigo = new Long(selectedSegSistemaCia.getSicCodigo());
            entity = businessDelegatorView.getSegSistemaCia(sicCodigo);
            businessDelegatorView.deleteSegSistemaCia(entity);
            data.remove(selectedSegSistemaCia);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long sicCodigo, String sicEstadoRegistro,
        Long ciaCodigo_SegCompania, Long sisCodigo_SegSistema,
        Long usuCodigo_SegUsuario) throws Exception {
        try {
            entity.setSicEstadoRegistro(FacesUtils.checkString(
                    sicEstadoRegistro));
            businessDelegatorView.updateSegSistemaCia(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegSistemaCiaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtSicEstadoRegistro() {
        return txtSicEstadoRegistro;
    }

    public void setTxtSicEstadoRegistro(InputText txtSicEstadoRegistro) {
        this.txtSicEstadoRegistro = txtSicEstadoRegistro;
    }

    public InputText getTxtCiaCodigo_SegCompania() {
        return txtCiaCodigo_SegCompania;
    }

    public void setTxtCiaCodigo_SegCompania(InputText txtCiaCodigo_SegCompania) {
        this.txtCiaCodigo_SegCompania = txtCiaCodigo_SegCompania;
    }

    public InputText getTxtSisCodigo_SegSistema() {
        return txtSisCodigo_SegSistema;
    }

    public void setTxtSisCodigo_SegSistema(InputText txtSisCodigo_SegSistema) {
        this.txtSisCodigo_SegSistema = txtSisCodigo_SegSistema;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public InputText getTxtSicCodigo() {
        return txtSicCodigo;
    }

    public void setTxtSicCodigo(InputText txtSicCodigo) {
        this.txtSicCodigo = txtSicCodigo;
    }

    public List<SegSistemaCiaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSegSistemaCia();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SegSistemaCiaDTO> segSistemaCiaDTO) {
        this.data = segSistemaCiaDTO;
    }

    public SegSistemaCiaDTO getSelectedSegSistemaCia() {
        return selectedSegSistemaCia;
    }

    public void setSelectedSegSistemaCia(SegSistemaCiaDTO segSistemaCia) {
        this.selectedSegSistemaCia = segSistemaCia;
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
