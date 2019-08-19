package com.vortexbird.sentinel.presentation.backingBeans;

import org.apache.log4j.Logger;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegOpcionDTO;
import com.vortexbird.sentinel.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.sentinel.utilities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
@ManagedBean
@ViewScoped
public class SegOpcionView {
    private InputText txtOpcDescripcion;
    private SelectOneMenu somEstadosRegistro;
    private SelectOneMenu somGrupoOpcionPadre;
    private SelectItem[] losGrupoOpcionPadre;
    private InputText txtOpcLlaveAcceso;
    private InputText txtOpcNombre;
    private InputText txtGruCodigo_SegGrupoOpcion;
    private InputText txtUsuCodigo_SegUsuario;
    private InputText txtOpcCodigo;
    private InputText txtOrden;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SegOpcionDTO> data;
    private SegOpcionDTO selectedSegOpcion;
    private Logger logger = Logger.getLogger(SegOpcionView.class);
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
    
    public SegOpcionView() {
        super();
    }


	public String action_clear() {
		txtOpcDescripcion.setValue(null);
		txtOpcDescripcion.setDisabled(false);
		somEstadosRegistro.setValue("-1");
		somGrupoOpcionPadre.setValue("-1");
		txtOpcLlaveAcceso.setValue(null);
		txtOpcLlaveAcceso.setDisabled(false);
		txtOpcNombre.setValue(null);
		txtOrden.setValue(null);
		txtOpcCodigo.setValue(null);
		txtOpcNombre.setDisabled(false);
		txtOrden.setDisabled(false);
		btnSave.setDisabled(false);
		btnModify.setDisabled(true);
		btnClear.setDisabled(false);
		return "";
	}
	
	public String action_selected(){

		action_clear();
		btnModify.setDisabled(false);
		btnSave.setDisabled(true);

		FacesContext context = FacesContext.getCurrentInstance();  
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();

		String opcCodigo = (String)requestMap.get("opcCodigo");

		try {

			SegOpcion entity = businessDelegatorView.getSegOpcion(Long.parseLong(opcCodigo));
			
			txtOpcDescripcion.setValue(entity.getOpcDescripcion());
			somEstadosRegistro.setValue(entity.getOpcEstadoRegistro());
			txtOpcLlaveAcceso.setValue(entity.getOpcLlaveAcceso());
			txtOpcNombre.setValue(entity.getOpcNombre());
			txtOpcCodigo.setValue(entity.getOpcCodigo());
			txtOrden.setValue(entity.getOrden());
			
			if (entity.getSegGrupoOpcion()!=null) {
				somGrupoOpcionPadre.setValue(entity.getSegGrupoOpcion().getGruCodigo());
			}else {
				somGrupoOpcionPadre.setValue(1L);
			}
			btnSave.setDisabled(true);
			btnModify.setDisabled(false);

		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}

    public String action_save() {
    	try {
			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();

			if (txtOpcNombre.getValue()==null||txtOpcNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El nombre de la opción no puede estar vacío");
			}

			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro válido");
			}

			if (txtOpcLlaveAcceso.getValue()==null||txtOpcLlaveAcceso.getValue().toString().equals("")==true ) {
				throw new Exception("La llave de acceso de la opción no puede estar vacía");
			}
			
			if (txtOpcDescripcion.getValue()==null||txtOpcDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La descripción de la opción no puede estar vacía");
			}
			
			if (somGrupoOpcionPadre.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un grupo opción válido");
			}
			
			if (txtOrden.getValue() == null || txtOrden.getValue().toString().equals("")){
				throw new Exception("Debe ingresar un número entero para la órden de la opción");
			}
			
			Integer orden = 0;
			try {
				orden = Integer.parseInt(txtOrden.getValue().toString());
			} catch (Exception e) {
				throw new Exception("Debe ingresar un número entero para la órden de la opción");
			}
			
			String descripcion = txtOpcDescripcion.getValue().toString();
			String estadoRegistro = somEstadosRegistro.getValue().toString();
			String llaveAcceso = txtOpcLlaveAcceso.getValue().toString();
			String nombre = txtOpcNombre.getValue().toString();
			Long codigoGrupo = Long.parseLong(somGrupoOpcionPadre.getValue().toString());
			
			
			businessDelegatorView.guardarOpcion(nombre, descripcion, llaveAcceso, codigoGrupo, usuSession, estadoRegistro, orden);
			
			//Se es el super administrador, se consultan todas las opciones de todos los sistemas
			if (usuSession == 0) {
				data = businessDelegatorView.getDataSegOpcion();	
			}else {
				//Si no es el super administrador, se consultan las opciones solo del sistema seleccionado
				data = businessDelegatorView.consultarOpcionesPorSistema(Long.parseLong(sistema));	
			}
			
			FacesUtils.addInfoMessage("Opción adcionada exitosamente");
			action_clear();
			
        } catch (Exception e) {
        	FacesUtils.addErrorMessage(e.getMessage());
        	logger.error(e.getMessage(), e);
        }

        return "";
    }

    public String action_modify() {
    	
    	Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
		
		try {

			if (txtOpcNombre.getValue()==null||txtOpcNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre de la opcion no puede estar vacio");
			}
			
			if (somEstadosRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtOpcLlaveAcceso.getValue()==null||txtOpcLlaveAcceso.getValue().toString().equals("")==true ) {
				throw new Exception("La llave de acceso de la opcion no puede estar vacia");
			}

			if (txtOpcDescripcion.getValue()==null||txtOpcDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La descripcion de la Opcion no puede estar vacia");
			}
			
			if (somGrupoOpcionPadre.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un grupo Opcion valido");
			}
			
			if (txtOrden.getValue() == null || txtOrden.getValue().toString().equals("")){
				throw new Exception("Debe ingresar un número entero para la órden de la opción");
			}
			
			Integer orden = 0;
			try {
				orden = Integer.parseInt(txtOrden.getValue().toString());
			} catch (Exception e) {
				throw new Exception("Debe ingresar un número entero para la órden de la opción");
			}
			
			Long codigo = Long.parseLong(txtOpcCodigo.getValue().toString());
			String descripcion = txtOpcDescripcion.getValue().toString();
			String estadoRegistro = somEstadosRegistro.getValue().toString();
			String llaveAcceso = txtOpcLlaveAcceso.getValue().toString();
			String nombre = txtOpcNombre.getValue().toString();
			Long codigoGrupo = Long.parseLong(somGrupoOpcionPadre.getValue().toString());
			
			businessDelegatorView.modificarOpcion(codigo, nombre, descripcion, llaveAcceso, codigoGrupo, usuSession, estadoRegistro, orden);
			
//			lasOpciones = BusinessDelegatorView.getSegOpcion();
//			data = BusinessDelegatorView.getSegOpcionesDTO(lasOpciones);
			
			String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
        	
			//Se es el super administrador, se consultan todas las opciones de todos los sistemas
			if (usuSession == 0) {
				data = businessDelegatorView.getDataSegOpcion();	
			}else {
				//Si no es el super administrador, se consultan las opciones solo del sistema seleccionado
				data = businessDelegatorView.consultarOpcionesPorSistema(Long.parseLong(sistema));	
			}
			
			FacesUtils.addInfoMessage("Opción modificada exitosamente");
			
			action_clear();
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
        return "";
    }

//    public String action_modifyWitDTO(Long opcCodigo, String opcDescripcion,
//        String opcEstadoRegistro, String opcLlaveAcceso, String opcNombre,
//        Long gruCodigo_SegGrupoOpcion, Long usuCodigo_SegUsuario)
//        throws Exception {
//        try {
//            BusinessDelegatorView.updateSegOpcion(opcCodigo, opcDescripcion,
//                opcEstadoRegistro, opcLlaveAcceso, opcNombre,
//                gruCodigo_SegGrupoOpcion, usuCodigo_SegUsuario);
//            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
//        } catch (Exception e) {
//            //renderManager.getOnDemandRenderer("SegOpcionView").requestRender();
//            FacesUtils.addErrorMessage(e.getMessage());
//            throw e;
//        }
//
//        return "";
//    }

    public InputText getTxtOpcDescripcion() {
        return txtOpcDescripcion;
    }

    public void setTxtOpcDescripcion(InputText txtOpcDescripcion) {
        this.txtOpcDescripcion = txtOpcDescripcion;
    }

    public SelectOneMenu getSomEstadosRegistro() {
		return somEstadosRegistro;
	}

	public void setSomEstadosRegistro(SelectOneMenu somEstadosRegistro) {
		this.somEstadosRegistro = somEstadosRegistro;
	}


	public InputText getTxtOpcLlaveAcceso() {
        return txtOpcLlaveAcceso;
    }

    public void setTxtOpcLlaveAcceso(InputText txtOpcLlaveAcceso) {
        this.txtOpcLlaveAcceso = txtOpcLlaveAcceso;
    }

    public InputText getTxtOpcNombre() {
        return txtOpcNombre;
    }

    public void setTxtOpcNombre(InputText txtOpcNombre) {
        this.txtOpcNombre = txtOpcNombre;
    }

    public InputText getTxtGruCodigo_SegGrupoOpcion() {
        return txtGruCodigo_SegGrupoOpcion;
    }

    public void setTxtGruCodigo_SegGrupoOpcion(
        InputText txtGruCodigo_SegGrupoOpcion) {
        this.txtGruCodigo_SegGrupoOpcion = txtGruCodigo_SegGrupoOpcion;
    }

    public InputText getTxtUsuCodigo_SegUsuario() {
        return txtUsuCodigo_SegUsuario;
    }

    public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
        this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
    }

    public InputText getTxtOpcCodigo() {
        return txtOpcCodigo;
    }

    public void setTxtOpcCodigo(InputText txtOpcCodigo) {
        this.txtOpcCodigo = txtOpcCodigo;
    }

    public List<SegOpcionDTO> getData() {
    	try {
            if (data == null) {

            	Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
            	String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
            	
				if (usuSession == 0) {
					data = businessDelegatorView.getDataSegOpcion();	
				}else {
					data = businessDelegatorView.consultarOpcionesPorSistema(Long.parseLong(sistema));	
				}
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void setData(List<SegOpcionDTO> segOpcionDTO) {
        this.data = segOpcionDTO;
    }

    public SegOpcionDTO getSelectedSegOpcion() {
        return selectedSegOpcion;
    }

    public void setSelectedSegOpcion(SegOpcionDTO segOpcion) {
        this.selectedSegOpcion = segOpcion;
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


	public SelectOneMenu getSomGrupoOpcionPadre() {
		return somGrupoOpcionPadre;
	}


	public void setSomGrupoOpcionPadre(SelectOneMenu somGrupoOpcionPadre) {
		this.somGrupoOpcionPadre = somGrupoOpcionPadre;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setLosGrupoOpcionPadre(SelectItem[] losGrupoOpcionPadre) {
		this.losGrupoOpcionPadre = losGrupoOpcionPadre;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}


	public InputText getTxtOrden() {
		return txtOrden;
	}


	public void setTxtOrden(InputText txtOrden) {
		this.txtOrden = txtOrden;
	}
	
	
	
}
