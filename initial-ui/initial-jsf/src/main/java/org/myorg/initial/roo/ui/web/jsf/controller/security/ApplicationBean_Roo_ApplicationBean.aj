// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.ui.web.jsf.controller.security;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.myorg.initial.roo.ui.web.jsf.controller.security.ApplicationBean;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

privileged aspect ApplicationBean_Roo_ApplicationBean {
    
    declare @type: ApplicationBean: @ManagedBean;
    
    declare @type: ApplicationBean: @RequestScoped;
    
    private MenuModel ApplicationBean.menuModel;
    
    @PostConstruct
    public void ApplicationBean.init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        menuModel = new DefaultMenuModel();
        Submenu submenu;
        MenuItem item;
        
        submenu = new Submenu();
        submenu.setId("authRoleSubmenu");
        submenu.setLabel("AuthRole");
        item = new MenuItem();
        item.setId("createAuthRoleMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_create}", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{authRoleBean.displayCreateDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setId("listAuthRoleMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list}", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{authRoleBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);
        
        submenu = new Submenu();
        submenu.setId("otherAddressSubmenu");
        submenu.setLabel("OtherAddress");
        item = new MenuItem();
        item.setId("createOtherAddressMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_create}", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{otherAddressBean.displayCreateDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setId("listOtherAddressMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list}", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{otherAddressBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);
        
        submenu = new Submenu();
        submenu.setId("personSubmenu");
        submenu.setLabel("Person");
        item = new MenuItem();
        item.setId("createPersonMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_create}", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{personBean.displayCreateDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setId("listPersonMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list}", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{personBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);
        
        submenu = new Submenu();
        submenu.setId("principalSubmenu");
        submenu.setLabel("Principal");
        item = new MenuItem();
        item.setId("createPrincipalMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_create}", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{principalBean.displayCreateDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setId("listPrincipalMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list}", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{principalBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);
        
        submenu = new Submenu();
        submenu.setId("workAddressSubmenu");
        submenu.setLabel("WorkAddress");
        item = new MenuItem();
        item.setId("createWorkAddressMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_create}", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{workAddressBean.displayCreateDialog}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setId("listWorkAddressMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_list}", String.class));
        item.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{workAddressBean.displayList}", String.class, new Class[0]));
        item.setIcon("ui-icon ui-icon-folder-open");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);
        menuModel.addSubmenu(submenu);
    }
    
    public MenuModel ApplicationBean.getMenuModel() {
        return menuModel;
    }
    
    public String ApplicationBean.getAppName() {
        return "Initial";
    }
    
}
