package com.vortexbird.sentinel.presentation.backingBeans;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegParametroDTO;
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
public class SegParametroView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegParametroView.class);
    private InputText txtParEstadoRegistro;
    private InputText txtParNombre;
    private InputText txtParValorAlfanumerico;
    private InputText txtParValorNumerico;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtParCodigo;
    private Calendar txtParValorFecha;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegParametroDTO> data;
    private SegParametroDTO selectedSegParametro;
    private SegParametro entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SegParametroView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegParametroDTO segParametroDTO = (SegParametroDTO) e.getObject();

            if (txtParEstadoRegistro == null) {
                txtParEstadoRegistro = new InputText();
            }

            txtParEstadoRegistro.setValue(segParametroDTO.getParEstadoRegistro());

            if (txtParNombre == null) {
                txtParNombre = new InputText();
            }

            txtParNombre.setValue(segParametroDTO.getParNombre());

            if (txtParValorAlfanumerico == null) {
                txtParValorAlfanumerico = new InputText();
            }

            txtParValorAlfanumerico.setValue(segParametroDTO.getParValorAlfanumerico());

            if (txtParValorNumerico == null) {
                txtParValorNumerico = new InputText();
            }

            txtParValorNumerico.setValue(segParametroDTO.getParValorNumerico());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segParametroDTO.getUsuCodigo_SegUsuario());

            if (txtParCodigo == null) {
                txtParCodigo = new InputText();
            }

            txtParCodigo.setValue(segParametroDTO.getParCodigo());

            if (txtParValorFecha == null) {
                txtParValorFecha = new Calendar();
            }

            txtParValorFecha.setValue(segParametroDTO.getParValorFecha());

            Long parCodigo = FacesUtils.checkLong(txtParCodigo);
            entity = businessDelegatorView.getSegParametro(parCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSegParametro = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSegParametro = null;

        if (txtParEstadoRegistro != null) {
            txtParEstadoRegistro.setValue(null);
            txtParEstadoRegistro.setDisabled(true);
        }

        if (txtParNombre != null) {
            txtParNombre.setValue(null);
            txtParNombre.setDisabled(true);
        }

        if (txtParValorAlfanumerico != null) {
            txtParValorAlfanumerico.setValue(null);
            txtParValorAlfanumerico.setDisabled(true);
        }

        if (txtParValorNumerico != null) {
            txtParValorNumerico.setValue(null);
            txtParValorNumerico.setDisabled(true);
        }

        if (txtUsuCodigo_SegUsuario != null) {
            txtUsuCodigo_SegUsuario.setValue(null);
            txtUsuCodigo_SegUsuario.setDisabled(true);
        }

        if (txtParValorFecha != null) {
            txtParValorFecha.setValue(null);
            txtParValorFecha.setDisabled(true);
        }

        if (txtParCodigo != null) {
            txtParCodigo.setValue(null);
            txtParCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtParValorFecha() {
        Date inputDate = (Date) txtParValorFecha.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long parCodigo = FacesUtils.checkLong(txtParCodigo);
            entity = (parCodigo != null)
                ? businessDelegatorView.getSegParametro(parCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtParEstadoRegistro.setDisabled(false);
            txtParNombre.setDisabled(false);
            txtParValorAlfanumerico.setDisabled(false);
            txtParValorNumerico.setDisabled(false);
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtParValorFecha.setDisabled(false);
            txtParCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtParEstadoRegistro.setValue(entity.getParEstadoRegistro());
            txtParEstadoRegistro.setDisabled(false);
            txtParNombre.setValue(entity.getParNombre());
            txtParNombre.setDisabled(false);
            txtParValorAlfanumerico.setValue(entity.getParValorAlfanumerico());
            txtParValorAlfanumerico.setDisabled(false);
            txtParValorFecha.setValue(entity.getParValorFecha());
            txtParValorFecha.setDisabled(false);
            txtParValorNumerico.setValue(entity.getParValorNumerico());
            txtParValorNumerico.setDisabled(false);
            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
                                                   .getUsuCodigo());
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtParCodigo.setValue(entity.getParCodigo());
            txtParCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSegParametro = (SegParametroDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedSegParametro"));
        txtParEstadoRegistro.setValue(selectedSegParametro.getParEstadoRegistro());
        txtParEstadoRegistro.setDisabled(false);
        txtParNombre.setValue(selectedSegParametro.getParNombre());
        txtParNombre.setDisabled(false);
        txtParValorAlfanumerico.setValue(selectedSegParametro.getParValorAlfanumerico());
        txtParValorAlfanumerico.setDisabled(false);
        txtParValorFecha.setValue(selectedSegParametro.getParValorFecha());
        txtParValorFecha.setDisabled(false);
        txtParValorNumerico.setValue(selectedSegParametro.getParValorNumerico());
        txtParValorNumerico.setDisabled(false);
        txtUsuCodigo_SegUsuario.setValue(selectedSegParametro.getUsuCodigo_SegUsuario());
        txtUsuCodigo_SegUsuario.setDisabled(false);
        txtParCodigo.setValue(selectedSegParametro.getParCodigo());
        txtParCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSegParametro == null) && (entity == null)) {
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
            entity = new SegParametro();

            Long parCodigo = FacesUtils.checkLong(txtParCodigo);

            entity.setParCodigo(parCodigo);
            entity.setParEstadoRegistro(FacesUtils.checkString(
                    txtParEstadoRegistro));
            entity.setParNombre(FacesUtils.checkString(txtParNombre));
            entity.setParValorAlfanumerico(FacesUtils.checkString(
                    txtParValorAlfanumerico));
            entity.setParValorFecha(FacesUtils.checkDate(txtParValorFecha));
            entity.setParValorNumerico(FacesUtils.checkDouble(
                    txtParValorNumerico));
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.saveSegParametro(entity);
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
                Long parCodigo = new Long(selectedSegParametro.getParCodigo());
                entity = businessDelegatorView.getSegParametro(parCodigo);
            }

            entity.setParEstadoRegistro(FacesUtils.checkString(
                    txtParEstadoRegistro));
            entity.setParNombre(FacesUtils.checkString(txtParNombre));
            entity.setParValorAlfanumerico(FacesUtils.checkString(
                    txtParValorAlfanumerico));
            entity.setParValorFecha(FacesUtils.checkDate(txtParValorFecha));
            entity.setParValorNumerico(FacesUtils.checkDouble(
                    txtParValorNumerico));
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            businessDelegatorView.updateSegParametro(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSegParametro = (SegParametroDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedSegParametro"));

            Long parCodigo = new Long(selectedSegParametro.getParCodigo());
            entity = businessDelegatorView.getSegParametro(parCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long parCodigo = FacesUtils.checkLong(txtParCodigo);
            entity = businessDelegatorView.getSegParametro(parCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSegParametro(entity);
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
            selectedSegParametro = (SegParametroDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedSegParametro"));

            Long parCodigo = new Long(selectedSegParametro.getParCodigo());
            entity = businessDelegatorView.getSegParametro(parCodigo);
            businessDelegatorView.deleteSegParametro(entity);
            data.remove(selectedSegParametro);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long parCodigo, String parEstadoRegistro,
        String parNombre, String parValorAlfanumerico, Date parValorFecha,
        Double parValorNumerico, Long usuCodigo_SegUsuario)
        throws Exception {
        try {
            entity.setParEstadoRegistro(FacesUtils.checkString(
                    parEstadoRegistro));
            entity.setParNombre(FacesUtils.checkString(parNombre));
            entity.setParValorAlfanumerico(FacesUtils.checkString(
                    parValorAlfanumerico));
            entity.setParValorFecha(FacesUtils.checkDate(parValorFecha));
            entity.setParValorNumerico(FacesUtils.checkDouble(parValorNumerico));
            businessDelegatorView.updateSegParametro(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SegParametroView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtParEstadoRegistro() {
        return txtParEstadoRegistro;
    }

    public void setTxtParEstadoRegistro(InputText txtParEstadoRegistro) {
        this.txtParEstadoRegistro = txtParEstadoRegistro;
    }

    public InputText getTxtParNombre() {
        return txtParNombre;
    }

    public void setTxtParNombre(InputText txtParNombre) {
        this.txtParNombre = txtParNombre;
    }

    public InputText getTxtParValorAlfanumerico() {
        return txtParValorAlfanumerico;
    }

    public void setTxtParValorAlfanumerico(InputText txtParValorAlfanumerico) {
        this.txtParValorAlfanumerico = txtParValorAlfanumerico;
    }

    public InputText getTxtParValorNumerico() {
        return txtParValorNumerico;
    }

    public void setTxtParValorNumerico(InputText txtParValorNumerico) {
        this.txtParValorNumerico = txtParValorNumerico;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public Calendar getTxtParValorFecha() {
        return txtParValorFecha;
    }

    public void setTxtParValorFecha(Calendar txtParValorFecha) {
        this.txtParValorFecha = txtParValorFecha;
    }

    public InputText getTxtParCodigo() {
        return txtParCodigo;
    }

    public void setTxtParCodigo(InputText txtParCodigo) {
        this.txtParCodigo = txtParCodigo;
    }

    public List<SegParametroDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSegParametro();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SegParametroDTO> segParametroDTO) {
        this.data = segParametroDTO;
    }

    public SegParametroDTO getSelectedSegParametro() {
        return selectedSegParametro;
    }

    public void setSelectedSegParametro(SegParametroDTO segParametro) {
        this.selectedSegParametro = segParametro;
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
