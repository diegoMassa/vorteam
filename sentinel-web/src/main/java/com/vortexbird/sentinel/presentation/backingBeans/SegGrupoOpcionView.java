package com.vortexbird.sentinel.presentation.backingBeans;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegGrupoOpcionDTO;
import com.vortexbird.sentinel.presentation.businessDelegate.*;
import com.vortexbird.sentinel.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
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
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class SegGrupoOpcionView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SegGrupoOpcionView.class);
    private InputText txtGruDescripcion;
    private InputText txtGruEstadoRegistro;
    private InputText txtGruIcono;
    private InputText txtGruLlaveAcceso;
    private InputText txtGruNombre;
    private InputText txtGruCodigo_SegGrupoOpcion;
    private InputText txtSisCodigo_SegSistema;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtGruCodigo;
    private InputText txtOrden;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegGrupoOpcionDTO> data;
    private SegGrupoOpcionDTO selectedSegGrupoOpcion;
    private SegGrupoOpcion entity;
    private boolean showDialog;
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    
    
	private SelectOneMenu somEstadosRegistro;
	private SelectOneMenu somGrupoOpcionPadre;
	private SelectItem[] losGrupoOpcionPadre;
	private SelectOneMenu somSistemas;
	private SelectItem[] losSistemas;

    public SegGrupoOpcionView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SegGrupoOpcionDTO segGrupoOpcionDTO = (SegGrupoOpcionDTO) e.getObject();

            if (txtGruDescripcion == null) {
                txtGruDescripcion = new InputText();
            }

            txtGruDescripcion.setValue(segGrupoOpcionDTO.getGruDescripcion());

            if (txtGruEstadoRegistro == null) {
                txtGruEstadoRegistro = new InputText();
            }

            txtGruEstadoRegistro.setValue(segGrupoOpcionDTO.getGruEstadoRegistro());

            if (txtGruIcono == null) {
                txtGruIcono = new InputText();
            }

            //txtGruIcono.setValue(segGrupoOpcionDTO.getGruIcono());

            if (txtGruLlaveAcceso == null) {
                txtGruLlaveAcceso = new InputText();
            }

            txtGruLlaveAcceso.setValue(segGrupoOpcionDTO.getGruLlaveAcceso());

            if (txtGruNombre == null) {
                txtGruNombre = new InputText();
            }

            txtGruNombre.setValue(segGrupoOpcionDTO.getGruNombre());

            if (txtGruCodigo_SegGrupoOpcion == null) {
                txtGruCodigo_SegGrupoOpcion = new InputText();
            }

            txtGruCodigo_SegGrupoOpcion.setValue(segGrupoOpcionDTO.getGruCodigo_SegGrupoOpcion());

            if (txtSisCodigo_SegSistema == null) {
                txtSisCodigo_SegSistema = new InputText();
            }

            txtSisCodigo_SegSistema.setValue(segGrupoOpcionDTO.getSisCodigo_SegSistema());

            if (txtOrden == null) {
            	txtOrden = new InputText();
            }
            
            txtOrden.setValue(segGrupoOpcionDTO.getOrden());

            if (txtUsuCodigo_SegUsuario == null) {
                txtUsuCodigo_SegUsuario = new InputText();
            }

            txtUsuCodigo_SegUsuario.setValue(segGrupoOpcionDTO.getUsuCodigo_SegUsuario());

            if (txtGruCodigo == null) {
                txtGruCodigo = new InputText();
            }

            txtGruCodigo.setValue(segGrupoOpcionDTO.getGruCodigo());

            Long gruCodigo = FacesUtils.checkLong(txtGruCodigo);
            entity = businessDelegatorView.getSegGrupoOpcion(gruCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSegGrupoOpcion = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
		txtGruDescripcion.setValue(null);
		txtGruDescripcion.setDisabled(false);
		txtGruLlaveAcceso.setValue(null);
		txtGruLlaveAcceso.setDisabled(false);
		txtGruNombre.setValue(null);
		txtOrden.setValue(null);
		txtOrden.setDisabled(false);
		txtGruNombre.setDisabled(false);
		somEstadosRegistro.setValue("-1");
		txtGruCodigo.setValue(null);
		somGrupoOpcionPadre.setValue("-1");
		somSistemas.setValue("-1");
		btnSave.setDisabled(false);
		btnModify.setDisabled(true);
		btnClear.setDisabled(false);
		return "";
	}

    public void listener_txtId() {
        try {
            Long gruCodigo = FacesUtils.checkLong(txtGruCodigo);
            entity = (gruCodigo != null)
                ? businessDelegatorView.getSegGrupoOpcion(gruCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtGruDescripcion.setDisabled(false);
            txtGruEstadoRegistro.setDisabled(false);
            txtGruIcono.setDisabled(false);
            txtGruLlaveAcceso.setDisabled(false);
            txtGruNombre.setDisabled(false);
            txtGruCodigo_SegGrupoOpcion.setDisabled(false);
            txtSisCodigo_SegSistema.setDisabled(false);
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtGruCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtGruDescripcion.setValue(entity.getGruDescripcion());
            txtGruDescripcion.setDisabled(false);
            txtGruEstadoRegistro.setValue(entity.getGruEstadoRegistro());
            txtGruEstadoRegistro.setDisabled(false);
            txtGruIcono.setValue(entity.getGruIcono());
            txtGruIcono.setDisabled(false);
            txtGruLlaveAcceso.setValue(entity.getGruLlaveAcceso());
            txtGruLlaveAcceso.setDisabled(false);
            txtGruNombre.setValue(entity.getGruNombre());
            txtGruNombre.setDisabled(false);
            txtGruCodigo_SegGrupoOpcion.setValue(entity.getSegGrupoOpcion()
                                                       .getGruCodigo());
            txtGruCodigo_SegGrupoOpcion.setDisabled(false);
            txtSisCodigo_SegSistema.setValue(entity.getSegSistema()
                                                   .getSisCodigo());
            txtSisCodigo_SegSistema.setDisabled(false);
            txtUsuCodigo_SegUsuario.setValue(entity.getSegUsuario()
                                                   .getUsuCodigo());
            txtUsuCodigo_SegUsuario.setDisabled(false);
            txtGruCodigo.setValue(entity.getGruCodigo());
            txtGruCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSegGrupoOpcion = (SegGrupoOpcionDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedSegGrupoOpcion"));
        txtGruDescripcion.setValue(selectedSegGrupoOpcion.getGruDescripcion());
        txtGruDescripcion.setDisabled(false);
        txtGruEstadoRegistro.setValue(selectedSegGrupoOpcion.getGruEstadoRegistro());
        txtGruEstadoRegistro.setDisabled(false);
        //txtGruIcono.setValue(selectedSegGrupoOpcion.getGruIcono());
        txtGruIcono.setDisabled(false);
        txtGruLlaveAcceso.setValue(selectedSegGrupoOpcion.getGruLlaveAcceso());
        txtGruLlaveAcceso.setDisabled(false);
        txtGruNombre.setValue(selectedSegGrupoOpcion.getGruNombre());
        txtGruNombre.setDisabled(false);
        txtGruCodigo_SegGrupoOpcion.setValue(selectedSegGrupoOpcion.getGruCodigo_SegGrupoOpcion());
        txtGruCodigo_SegGrupoOpcion.setDisabled(false);
        txtSisCodigo_SegSistema.setValue(selectedSegGrupoOpcion.getSisCodigo_SegSistema());
        txtSisCodigo_SegSistema.setDisabled(false);
        txtUsuCodigo_SegUsuario.setValue(selectedSegGrupoOpcion.getUsuCodigo_SegUsuario());
        txtUsuCodigo_SegUsuario.setDisabled(false);
        txtGruCodigo.setValue(selectedSegGrupoOpcion.getGruCodigo());
        txtGruCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
		try {
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();

			if (txtGruNombre.getValue()==null||txtGruNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre del Grupo no puede estar vacio");
			}

			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtGruDescripcion.getValue()==null||txtGruDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La descripcion del grupo no puede estar vacia");
			}

			if (somSistemas.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un sistema valido");
			}

			Long codigoGrupoOpcionPadre=null;
			String descripcion = txtGruDescripcion.getValue().toString();
			String estadoRegistro = somEstadosRegistro.getValue().toString();
			String llaveAceso = txtGruLlaveAcceso.getValue().toString();
			String nombre = txtGruNombre.getValue().toString();

			if (somGrupoOpcionPadre.getValue().toString().equals("-1")== false) {
				codigoGrupoOpcionPadre = Long.parseLong(somGrupoOpcionPadre.getValue().toString());
			}
			Long codigoSistema = Long.parseLong(somSistemas.getValue().toString());

			businessDelegatorView.guardarGrupoOpcion(
					null , descripcion, estadoRegistro, llaveAceso,
					nombre,	codigoGrupoOpcionPadre,	codigoSistema,
					usuSession);

			if (usuSession == 0) {
				data = businessDelegatorView.getDataSegGrupoOpcion();	
			}else {
				List<SegGrupoOpcion> gruposDeOpciones = businessDelegatorView.consultarGrupoOpcionesPorSistema(Long.parseLong(sistema));
				if (gruposDeOpciones!=null){
					data = new ArrayList<SegGrupoOpcionDTO>();
					for (SegGrupoOpcion segGrupoOpcionTmp : gruposDeOpciones) {

						SegGrupoOpcionDTO segGrupoOpcionDTO2 = new SegGrupoOpcionDTO();
						segGrupoOpcionDTO2.setGruCodigo(segGrupoOpcionTmp.getGruCodigo().toString());
						segGrupoOpcionDTO2.setGruDescripcion((segGrupoOpcionTmp.getGruDescripcion() != null)? segGrupoOpcionTmp.getGruDescripcion() : null);
						segGrupoOpcionDTO2.setGruEstadoRegistro((segGrupoOpcionTmp.getGruEstadoRegistro().equals("0"))? "Inactivo" : "Activo");
						segGrupoOpcionDTO2.setGruLlaveAcceso((segGrupoOpcionTmp.getGruLlaveAcceso() != null)? segGrupoOpcionTmp.getGruLlaveAcceso() : null);
						segGrupoOpcionDTO2.setGruNombre((segGrupoOpcionTmp.getGruNombre() != null)? segGrupoOpcionTmp.getGruNombre() : null);
						segGrupoOpcionDTO2.setGruCodigo_SegGrupoOpcion((segGrupoOpcionTmp.getSegGrupoOpcion() != null)? segGrupoOpcionTmp.getSegGrupoOpcion().getGruCodigo().toString() : null);
						segGrupoOpcionDTO2.setSegNombre_SegGrupoPadre((segGrupoOpcionTmp.getSegGrupoOpcion() != null)? segGrupoOpcionTmp.getSegGrupoOpcion().getGruNombre() : null);
						segGrupoOpcionDTO2.setSisCodigo_SegSistema((segGrupoOpcionTmp.getSegSistema().getSisCodigo() != null)? segGrupoOpcionTmp.getSegSistema().getSisCodigo().toString() : null);
						segGrupoOpcionDTO2.setSisNombre_SegSistema((segGrupoOpcionTmp.getSegSistema()!=null)?segGrupoOpcionTmp.getSegSistema().getSisNombre():null);
						segGrupoOpcionDTO2.setUsuCodigo_SegUsuario((segGrupoOpcionTmp.getSegUsuario() != null)? segGrupoOpcionTmp.getSegUsuario().getUsuNombres() : null);
						segGrupoOpcionDTO2.setOrden((segGrupoOpcionTmp.getOrden() != null)? segGrupoOpcionTmp.getOrden() : null);

						data.add(segGrupoOpcionDTO2);
					}
				}
			}

			FacesUtils.addInfoMessage("Grupo adicionado exitosamente");
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}
    
    public String action_selected(){

		action_clear();
		btnModify.setDisabled(false);
		btnSave.setDisabled(true);

		FacesContext context = FacesContext.getCurrentInstance();  
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();

		String gruCodigo = (String)requestMap.get("gruCodigo");

		try {

			SegGrupoOpcion entity = businessDelegatorView.getSegGrupoOpcion(Long.parseLong(gruCodigo));
			
			txtGruDescripcion.setValue(entity.getGruDescripcion());
			somEstadosRegistro.setValue(entity.getGruEstadoRegistro());
			txtGruLlaveAcceso.setValue(entity.getGruLlaveAcceso());
			txtGruNombre.setValue(entity.getGruNombre());
			txtGruCodigo.setValue(entity.getGruCodigo());
			txtOrden.setValue(entity.getOrden());

			if (entity.getSegGrupoOpcion()!=null) {
				somGrupoOpcionPadre.setValue(entity.getSegGrupoOpcion().getGruCodigo());
			}else {
				somGrupoOpcionPadre.setValue("-1");
			}
			somSistemas.setValue(entity.getSegSistema().getSisCodigo());
			btnSave.setDisabled(true);
			btnModify.setDisabled(false);

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

    public String action_create() {
        try {
            entity = new SegGrupoOpcion();

            Long gruCodigo = FacesUtils.checkLong(txtGruCodigo);

            entity.setGruCodigo(gruCodigo);
            entity.setGruDescripcion(FacesUtils.checkString(txtGruDescripcion));
            entity.setGruEstadoRegistro(FacesUtils.checkString(
                    txtGruEstadoRegistro));
            entity.setGruIcono(FacesUtils.checkString(txtGruIcono));
            entity.setGruLlaveAcceso(FacesUtils.checkString(txtGruLlaveAcceso));
            entity.setGruNombre(FacesUtils.checkString(txtGruNombre));
            entity.setSegGrupoOpcion((FacesUtils.checkLong(
                    txtGruCodigo_SegGrupoOpcion) != null)
                ? businessDelegatorView.getSegGrupoOpcion(FacesUtils.checkLong(
                        txtGruCodigo_SegGrupoOpcion)) : null);
            entity.setSegSistema((FacesUtils.checkLong(txtSisCodigo_SegSistema) != null)
                ? businessDelegatorView.getSegSistema(FacesUtils.checkLong(
                        txtSisCodigo_SegSistema)) : null);
            entity.setSegUsuario((FacesUtils.checkLong(txtUsuCodigo_SegUsuario) != null)
                ? businessDelegatorView.getSegUsuario(FacesUtils.checkLong(
                        txtUsuCodigo_SegUsuario)) : null);
            
            businessDelegatorView.saveSegGrupoOpcion(entity);
            
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {

		List<SegGrupoOpcion> losGrupoOpcions = new ArrayList<SegGrupoOpcion>();
		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

		try {

			if (txtGruNombre.getValue()==null||txtGruNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre del Grupo no puede estar vacio");
			}

			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtGruDescripcion.getValue()==null||txtGruDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La descripcion del grupo no puede estar vacia");
			}

			if (somSistemas.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un sistema valido");
			}

			Long grupoCodigo = Long.parseLong(txtGruCodigo.getValue().toString());
			Long codigoGrupoOpcionPadre = null;
			String descripcion = txtGruDescripcion.getValue().toString();
			String estadoRegistro = somEstadosRegistro.getValue().toString();
			String llaveAceso = txtGruLlaveAcceso.getValue().toString();
			String nombre = txtGruNombre.getValue().toString();
			if (somGrupoOpcionPadre.getValue().toString().equals("-1")== false) {
				codigoGrupoOpcionPadre = Long.parseLong(somGrupoOpcionPadre.getValue().toString());
			}
			Long codigoSistema = Long.parseLong(somSistemas.getValue().toString());

			businessDelegatorView.modificarGrupoOpcion(
					grupoCodigo , descripcion, estadoRegistro, llaveAceso,
					nombre,	codigoGrupoOpcionPadre,	codigoSistema,
					usuSession);

//			losGrupoOpcions = BusinessDelegatorView.getSegGrupoOpcion();
//			data = BusinessDelegatorView.getSegGrupoOpcion(losGrupoOpcions);
			
			if (usuSession == 0) {
				data = businessDelegatorView.getDataSegGrupoOpcion();	
			}else {
				String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();

				List<SegGrupoOpcion> gruposDeOpciones = businessDelegatorView.consultarGrupoOpcionesPorSistema(Long.parseLong(sistema));
				if (gruposDeOpciones!=null){
					data = new ArrayList<SegGrupoOpcionDTO>();
					for (SegGrupoOpcion segGrupoOpcionTmp : gruposDeOpciones) {

						SegGrupoOpcionDTO segGrupoOpcionDTO2 = new SegGrupoOpcionDTO();
						segGrupoOpcionDTO2.setGruCodigo(segGrupoOpcionTmp.getGruCodigo().toString());
						segGrupoOpcionDTO2.setGruDescripcion((segGrupoOpcionTmp.getGruDescripcion() != null)? segGrupoOpcionTmp.getGruDescripcion() : null);
						segGrupoOpcionDTO2.setGruEstadoRegistro((segGrupoOpcionTmp.getGruEstadoRegistro().equals("0"))? "Inactivo" : "Activo");
						segGrupoOpcionDTO2.setGruLlaveAcceso((segGrupoOpcionTmp.getGruLlaveAcceso() != null)? segGrupoOpcionTmp.getGruLlaveAcceso() : null);
						segGrupoOpcionDTO2.setGruNombre((segGrupoOpcionTmp.getGruNombre() != null)? segGrupoOpcionTmp.getGruNombre() : null);
						segGrupoOpcionDTO2.setGruCodigo_SegGrupoOpcion((segGrupoOpcionTmp.getSegGrupoOpcion() != null)? segGrupoOpcionTmp.getSegGrupoOpcion().getGruCodigo().toString() : null);
						segGrupoOpcionDTO2.setSegNombre_SegGrupoPadre((segGrupoOpcionTmp.getSegGrupoOpcion() != null)? segGrupoOpcionTmp.getSegGrupoOpcion().getGruNombre() : null);
						segGrupoOpcionDTO2.setSisCodigo_SegSistema((segGrupoOpcionTmp.getSegSistema().getSisCodigo() != null)? segGrupoOpcionTmp.getSegSistema().getSisCodigo().toString() : null);
						segGrupoOpcionDTO2.setSisNombre_SegSistema((segGrupoOpcionTmp.getSegSistema()!=null)?segGrupoOpcionTmp.getSegSistema().getSisNombre():null);
						segGrupoOpcionDTO2.setUsuCodigo_SegUsuario((segGrupoOpcionTmp.getSegUsuario() != null)? segGrupoOpcionTmp.getSegUsuario().getUsuNombres() : null);
						segGrupoOpcionDTO2.setOrden((segGrupoOpcionTmp.getOrden() != null)? segGrupoOpcionTmp.getOrden() : null);

						data.add(segGrupoOpcionDTO2);
					}
				}
			
				
			}
			
			FacesUtils.addInfoMessage("Grupo opción modificado exitosamente");
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSegGrupoOpcion = (SegGrupoOpcionDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedSegGrupoOpcion"));

            Long gruCodigo = new Long(selectedSegGrupoOpcion.getGruCodigo());
            entity = businessDelegatorView.getSegGrupoOpcion(gruCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long gruCodigo = FacesUtils.checkLong(txtGruCodigo);
            entity = businessDelegatorView.getSegGrupoOpcion(gruCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSegGrupoOpcion(entity);
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
            selectedSegGrupoOpcion = (SegGrupoOpcionDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedSegGrupoOpcion"));

            Long gruCodigo = new Long(selectedSegGrupoOpcion.getGruCodigo());
            entity = businessDelegatorView.getSegGrupoOpcion(gruCodigo);
            businessDelegatorView.deleteSegGrupoOpcion(entity);
            data.remove(selectedSegGrupoOpcion);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long gruCodigo, String gruDescripcion,
        String gruEstadoRegistro, String gruIcono, String gruLlaveAcceso,
        String gruNombre, Long gruCodigo_SegGrupoOpcion,
        Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
        throws Exception {
		try {
//			BusinessDelegatorView.updateSegGrupoOpcion(gruCodigo,
//					gruDescripcion, gruEstadoRegistro, gruLlaveAcceso, gruNombre,
//					gruCodigo_SegGrupoOpcion, sisCodigo_SegSistema,
//					usuCodigo_SegUsuario);
//			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			//renderManager.getOnDemandRenderer("SegGrupoOpcionView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

    public InputText getTxtGruDescripcion() {
        return txtGruDescripcion;
    }

    public void setTxtGruDescripcion(InputText txtGruDescripcion) {
        this.txtGruDescripcion = txtGruDescripcion;
    }

    public InputText getTxtGruEstadoRegistro() {
        return txtGruEstadoRegistro;
    }

    public void setTxtGruEstadoRegistro(InputText txtGruEstadoRegistro) {
        this.txtGruEstadoRegistro = txtGruEstadoRegistro;
    }

    public InputText getTxtGruIcono() {
        return txtGruIcono;
    }

    public void setTxtGruIcono(InputText txtGruIcono) {
        this.txtGruIcono = txtGruIcono;
    }

    public InputText getTxtGruLlaveAcceso() {
        return txtGruLlaveAcceso;
    }

    public void setTxtGruLlaveAcceso(InputText txtGruLlaveAcceso) {
        this.txtGruLlaveAcceso = txtGruLlaveAcceso;
    }

    public InputText getTxtGruNombre() {
        return txtGruNombre;
    }

    public void setTxtGruNombre(InputText txtGruNombre) {
        this.txtGruNombre = txtGruNombre;
    }

    public InputText getTxtGruCodigo_SegGrupoOpcion() {
        return txtGruCodigo_SegGrupoOpcion;
    }

    public void setTxtGruCodigo_SegGrupoOpcion(
        InputText txtGruCodigo_SegGrupoOpcion) {
        this.txtGruCodigo_SegGrupoOpcion = txtGruCodigo_SegGrupoOpcion;
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

    public InputText getTxtGruCodigo() {
        return txtGruCodigo;
    }

    public void setTxtGruCodigo(InputText txtGruCodigo) {
        this.txtGruCodigo = txtGruCodigo;
    }

    public List<SegGrupoOpcionDTO> getData() {
		try {
//			if (data == null) {
				Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

				if (usuSession == 0) {
					data = businessDelegatorView.getDataSegGrupoOpcion();	
				}else {
					String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();

					List<SegGrupoOpcion> gruposDeOpciones = businessDelegatorView.consultarGrupoOpcionesPorSistema(Long.parseLong(sistema));
					if (gruposDeOpciones!=null){
						data = new ArrayList<SegGrupoOpcionDTO>();
						for (SegGrupoOpcion segGrupoOpcionTmp : gruposDeOpciones) {

							SegGrupoOpcionDTO segGrupoOpcionDTO2 = new SegGrupoOpcionDTO();
							segGrupoOpcionDTO2.setGruCodigo(segGrupoOpcionTmp.getGruCodigo().toString());
							segGrupoOpcionDTO2.setGruDescripcion((segGrupoOpcionTmp.getGruDescripcion() != null)? segGrupoOpcionTmp.getGruDescripcion() : null);
							segGrupoOpcionDTO2.setGruEstadoRegistro((segGrupoOpcionTmp.getGruEstadoRegistro().equals("0"))? "Inactivo" : "Activo");
							segGrupoOpcionDTO2.setGruLlaveAcceso((segGrupoOpcionTmp.getGruLlaveAcceso() != null)? segGrupoOpcionTmp.getGruLlaveAcceso() : null);
							segGrupoOpcionDTO2.setGruNombre((segGrupoOpcionTmp.getGruNombre() != null)? segGrupoOpcionTmp.getGruNombre() : null);
							segGrupoOpcionDTO2.setGruCodigo_SegGrupoOpcion((segGrupoOpcionTmp.getSegGrupoOpcion() != null)? segGrupoOpcionTmp.getSegGrupoOpcion().getGruCodigo().toString() : null);
							segGrupoOpcionDTO2.setSegNombre_SegGrupoPadre((segGrupoOpcionTmp.getSegGrupoOpcion() != null)? segGrupoOpcionTmp.getSegGrupoOpcion().getGruNombre() : null);
							segGrupoOpcionDTO2.setSisCodigo_SegSistema((segGrupoOpcionTmp.getSegSistema().getSisCodigo() != null)? segGrupoOpcionTmp.getSegSistema().getSisCodigo().toString() : null);
							segGrupoOpcionDTO2.setSisNombre_SegSistema((segGrupoOpcionTmp.getSegSistema()!=null)?segGrupoOpcionTmp.getSegSistema().getSisNombre():null);
							segGrupoOpcionDTO2.setUsuCodigo_SegUsuario((segGrupoOpcionTmp.getSegUsuario() != null)? segGrupoOpcionTmp.getSegUsuario().getUsuNombres() : null);
							segGrupoOpcionDTO2.setOrden((segGrupoOpcionTmp.getOrden() != null)? segGrupoOpcionTmp.getOrden() : null);

							data.add(segGrupoOpcionDTO2);
						}
					}
				
					
				}
				
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}
    
    public SelectItem[] getLosGrupoOpcionPadre() {

		try {
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			List<SegGrupoOpcion> listGrupoOpcion = new ArrayList<SegGrupoOpcion>();
			
			if (usuSession==0) {
				listGrupoOpcion=businessDelegatorView.getSegGrupoOpcion();
			}else {
				String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
				listGrupoOpcion=businessDelegatorView.consultarGrupoOpcionesPorSistema(Long.parseLong(sistema));
			}
			losGrupoOpcionPadre=new SelectItem[listGrupoOpcion.size()];
			int i=0;
			for (SegGrupoOpcion grupoOpcion : listGrupoOpcion) {
				losGrupoOpcionPadre[i]= new SelectItem(grupoOpcion.getGruCodigo(),grupoOpcion.getGruNombre().toString()+((grupoOpcion.getGruDescripcion()!=null)?" - "+grupoOpcion.getGruDescripcion():""));
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return losGrupoOpcionPadre;
	}
    
    public SelectItem[] getLosSistemas() {
		try {
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			List<SegSistema> listSistemas = new ArrayList<SegSistema>();
			
			if (usuSession == 0) {
				listSistemas=businessDelegatorView.getSegSistema();
			}else {
				//String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
				listSistemas = businessDelegatorView.consultarSistemasDeUsuarioAdministrador(usuSession);
			}
			
			losSistemas=new SelectItem[listSistemas.size()];
			int i=0;
			for (SegSistema segSistema : listSistemas) {
				losSistemas[i]= new SelectItem(segSistema.getSisCodigo(),segSistema.getSisNombre().toString());
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return losSistemas;
	}

    public void setData(List<SegGrupoOpcionDTO> segGrupoOpcionDTO) {
        this.data = segGrupoOpcionDTO;
    }

    public SegGrupoOpcionDTO getSelectedSegGrupoOpcion() {
        return selectedSegGrupoOpcion;
    }

    public void setSelectedSegGrupoOpcion(SegGrupoOpcionDTO segGrupoOpcion) {
        this.selectedSegGrupoOpcion = segGrupoOpcion;
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

	public SegGrupoOpcion getEntity() {
		return entity;
	}

	public void setEntity(SegGrupoOpcion entity) {
		this.entity = entity;
	}

	public SelectOneMenu getSomEstadosRegistro() {
		return somEstadosRegistro;
	}

	public void setSomEstadosRegistro(SelectOneMenu somEstadosRegistro) {
		this.somEstadosRegistro = somEstadosRegistro;
	}

	public SelectOneMenu getSomGrupoOpcionPadre() {
		return somGrupoOpcionPadre;
	}

	public void setSomGrupoOpcionPadre(SelectOneMenu somGrupoOpcionPadre) {
		this.somGrupoOpcionPadre = somGrupoOpcionPadre;
	}

	public SelectOneMenu getSomSistemas() {
		return somSistemas;
	}

	public void setSomSistemas(SelectOneMenu somSistemas) {
		this.somSistemas = somSistemas;
	}

	public void setLosGrupoOpcionPadre(SelectItem[] losGrupoOpcionPadre) {
		this.losGrupoOpcionPadre = losGrupoOpcionPadre;
	}

	public void setLosSistemas(SelectItem[] losSistemas) {
		this.losSistemas = losSistemas;
	}

	public InputText getTxtOrden() {
		return txtOrden;
	}

	public void setTxtOrden(InputText txtOrden) {
		this.txtOrden = txtOrden;
	}
	
}
