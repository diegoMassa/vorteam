package com.vortexbird.sentinel.presentation.backingBeans;

import javax.swing.tree.DefaultMutableTreeNode;

import com.vortexbird.sentinel.modelo.SegRol;
import com.vortexbird.sentinel.modelo.dto.SegTreeDTO;


public class RolUserObject extends NodeUserObject{

    private SegRol rol;
    private SegTreeDTO treeDTO;
    private boolean flagSeleccionado;
    private boolean flagVisibleFather=false;;
    private boolean flagVisibleChild=true;
    
    public RolUserObject(DefaultMutableTreeNode defaultMutableTreeNode) {
        super(defaultMutableTreeNode);
    }

	public SegRol getRol() {
		return rol;
	}

	public void setRol(SegRol rol) {
		this.rol = rol;
	}

	public SegTreeDTO getTreeDTO() {
		return treeDTO;
	}

	public void setTreeDTO(SegTreeDTO treeDTO) {
		this.treeDTO = treeDTO;
	}

	public boolean isFlagSeleccionado() {
		return flagSeleccionado;
	}

	public void setFlagSeleccionado(boolean flagSeleccionado) {
		this.flagSeleccionado = flagSeleccionado;
	}

	public boolean isFlagVisibleFather() {
		return flagVisibleFather;
	}

	public void setFlagVisibleFather(boolean flagVisibleFather) {
		this.flagVisibleFather = flagVisibleFather;
	}

	public boolean isFlagVisibleChild() {
		return flagVisibleChild;
	}

	public void setFlagVisibleChild(boolean flagVisibleChild) {
		this.flagVisibleChild = flagVisibleChild;
	}
	
}