package com.vortexbird.vorteam.view;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.vortexbird.vorteam.utility.FacesUtils;

@ManagedBean
@SessionScoped
public class LocaleBean {

	private Locale locale;

	@PostConstruct
	public void init() {

		try {
			if (FacesUtils.getfromSession("locale") != null) {
				this.locale = new Locale((String) FacesUtils.getfromSession("locale"));
				FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
			} else {
				this.locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
			}
		} catch (Exception e) {
			this.locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
		}
	}

	public Locale getLocale() {
		return locale;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public void setLanguage(String language) {
		if (language.trim().equals("es_CO")) {
			this.locale = new Locale.Builder().setLanguage("es").setRegion("CO").build();
		} else {
			this.locale = new Locale.Builder().setLanguage("en").setRegion("US").build();
		}
		FacesUtils.setManagedBeanInSession("locale", language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
	}
}