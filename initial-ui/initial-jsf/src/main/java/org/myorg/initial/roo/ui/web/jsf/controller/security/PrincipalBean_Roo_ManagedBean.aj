// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.ui.web.jsf.controller.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;
import javax.faces.validator.RegexValidator;
import org.myorg.initial.roo.core.domain.model.Person;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.myorg.initial.roo.ui.web.jsf.controller.security.PrincipalBean;
import org.myorg.initial.roo.ui.web.jsf.controller.security.converter.AuthRoleConverter;
import org.myorg.initial.roo.ui.web.jsf.controller.model.converter.PersonConverter;
import org.myorg.initial.roo.ui.web.jsf.controller.security.util.MessageFactory;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.component.selectmanymenu.SelectManyMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

privileged aspect PrincipalBean_Roo_ManagedBean {
    
    declare @type: PrincipalBean: @ManagedBean(name = "principalBean");
    
    declare @type: PrincipalBean: @SessionScoped;
    
    private String PrincipalBean.name = "Principals";
    
    private Principal PrincipalBean.principal;
    
    private List<Principal> PrincipalBean.allPrincipals;
    
    private boolean PrincipalBean.dataVisible = false;
    
    private List<String> PrincipalBean.columns;
    
    private HtmlPanelGrid PrincipalBean.createPanelGrid;
    
    private HtmlPanelGrid PrincipalBean.editPanelGrid;
    
    private HtmlPanelGrid PrincipalBean.viewPanelGrid;
    
    private boolean PrincipalBean.createDialogVisible = false;
    
    private List<AuthRole> PrincipalBean.selectedRoles;
    
    @PostConstruct
    public void PrincipalBean.init() {
        columns = new ArrayList<String>();
        columns.add("userName");
        columns.add("password");
        columns.add("activationKey");
    }
    
    public String PrincipalBean.getName() {
        return name;
    }
    
    public List<String> PrincipalBean.getColumns() {
        return columns;
    }
    
    public List<Principal> PrincipalBean.getAllPrincipals() {
        return allPrincipals;
    }
    
    public void PrincipalBean.setAllPrincipals(List<Principal> allPrincipals) {
        this.allPrincipals = allPrincipals;
    }
    
    public String PrincipalBean.findAllPrincipals() {
        allPrincipals = Principal.findAllPrincipals();
        dataVisible = !allPrincipals.isEmpty();
        return null;
    }
    
    public boolean PrincipalBean.isDataVisible() {
        return dataVisible;
    }
    
    public void PrincipalBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid PrincipalBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void PrincipalBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid PrincipalBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void PrincipalBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid PrincipalBean.getViewPanelGrid() {
        return populateViewPanel();
    }
    
    public void PrincipalBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public HtmlPanelGrid PrincipalBean.populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel userNameCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        userNameCreateOutput.setFor("userNameCreateInput");
        userNameCreateOutput.setId("userNameCreateOutput");
        userNameCreateOutput.setValue("User Name:");
        htmlPanelGrid.getChildren().add(userNameCreateOutput);
        
        InputTextarea userNameCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        userNameCreateInput.setId("userNameCreateInput");
        userNameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.userName}", String.class));
        LengthValidator userNameCreateInputValidator = new LengthValidator();
        userNameCreateInputValidator.setMinimum(3);
        userNameCreateInputValidator.setMaximum(50);
        userNameCreateInput.addValidator(userNameCreateInputValidator);
        RegexValidator userNameCreateInputRegexValidator = new RegexValidator();
        userNameCreateInputRegexValidator.setPattern(".+@.+\\.[a-z]+");
        userNameCreateInput.addValidator(userNameCreateInputRegexValidator);
        userNameCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(userNameCreateInput);
        
        Message userNameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        userNameCreateInputMessage.setId("userNameCreateInputMessage");
        userNameCreateInputMessage.setFor("userNameCreateInput");
        userNameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(userNameCreateInputMessage);
        
        OutputLabel passwordCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        passwordCreateOutput.setFor("passwordCreateInput");
        passwordCreateOutput.setId("passwordCreateOutput");
        passwordCreateOutput.setValue("Password:");
        htmlPanelGrid.getChildren().add(passwordCreateOutput);
        
        InputTextarea passwordCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        passwordCreateInput.setId("passwordCreateInput");
        passwordCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.password}", String.class));
        LengthValidator passwordCreateInputValidator = new LengthValidator();
        passwordCreateInputValidator.setMinimum(3);
        passwordCreateInputValidator.setMaximum(100);
        passwordCreateInput.addValidator(passwordCreateInputValidator);
        passwordCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(passwordCreateInput);
        
        Message passwordCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        passwordCreateInputMessage.setId("passwordCreateInputMessage");
        passwordCreateInputMessage.setFor("passwordCreateInput");
        passwordCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(passwordCreateInputMessage);
        
        OutputLabel enabledCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        enabledCreateOutput.setFor("enabledCreateInput");
        enabledCreateOutput.setId("enabledCreateOutput");
        enabledCreateOutput.setValue("Enabled:");
        htmlPanelGrid.getChildren().add(enabledCreateOutput);
        
        SelectBooleanCheckbox enabledCreateInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        enabledCreateInput.setId("enabledCreateInput");
        enabledCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.enabled}", Boolean.class));
        enabledCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(enabledCreateInput);
        
        Message enabledCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        enabledCreateInputMessage.setId("enabledCreateInputMessage");
        enabledCreateInputMessage.setFor("enabledCreateInput");
        enabledCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(enabledCreateInputMessage);
        
        OutputLabel activationKeyCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        activationKeyCreateOutput.setFor("activationKeyCreateInput");
        activationKeyCreateOutput.setId("activationKeyCreateOutput");
        activationKeyCreateOutput.setValue("Activation Key:");
        htmlPanelGrid.getChildren().add(activationKeyCreateOutput);
        
        InputTextarea activationKeyCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        activationKeyCreateInput.setId("activationKeyCreateInput");
        activationKeyCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.activationKey}", String.class));
        LengthValidator activationKeyCreateInputValidator = new LengthValidator();
        activationKeyCreateInputValidator.setMinimum(3);
        activationKeyCreateInputValidator.setMaximum(100);
        activationKeyCreateInput.addValidator(activationKeyCreateInputValidator);
        activationKeyCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(activationKeyCreateInput);
        
        Message activationKeyCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        activationKeyCreateInputMessage.setId("activationKeyCreateInputMessage");
        activationKeyCreateInputMessage.setFor("activationKeyCreateInput");
        activationKeyCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(activationKeyCreateInputMessage);
        
        OutputLabel rolesCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        rolesCreateOutput.setFor("rolesCreateInput");
        rolesCreateOutput.setId("rolesCreateOutput");
        rolesCreateOutput.setValue("Roles:");
        htmlPanelGrid.getChildren().add(rolesCreateOutput);
        
        SelectManyMenu rolesCreateInput = (SelectManyMenu) application.createComponent(SelectManyMenu.COMPONENT_TYPE);
        rolesCreateInput.setId("rolesCreateInput");
        rolesCreateInput.setConverter(new AuthRoleConverter());
        rolesCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.selectedRoles}", List.class));
        UISelectItems rolesCreateInputItems = (UISelectItems) application.createComponent(UISelectItems.COMPONENT_TYPE);
        rolesCreateInputItems.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{authRoleBean.allAuthRoles}", List.class));
        rolesCreateInput.setRequired(false);
        rolesCreateInputItems.setValueExpression("var", expressionFactory.createValueExpression(elContext, "authRole", String.class));
        rolesCreateInputItems.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{authRole}", String.class));
        rolesCreateInputItems.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{authRole}", AuthRole.class));
        rolesCreateInput.getChildren().add(rolesCreateInputItems);
        htmlPanelGrid.getChildren().add(rolesCreateInput);
        
        Message rolesCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        rolesCreateInputMessage.setId("rolesCreateInputMessage");
        rolesCreateInputMessage.setFor("rolesCreateInput");
        rolesCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(rolesCreateInputMessage);
        
        OutputLabel personCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        personCreateOutput.setFor("personCreateInput");
        personCreateOutput.setId("personCreateOutput");
        personCreateOutput.setValue("Person:");
        htmlPanelGrid.getChildren().add(personCreateOutput);
        
        AutoComplete personCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        personCreateInput.setId("personCreateInput");
        personCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.person}", Person.class));
        personCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{principalBean.completePerson}", List.class, new Class[] { String.class }));
        personCreateInput.setDropdown(true);
        personCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "person", String.class));
        personCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{person.firstName} #{person.lastName} #{person.lastName2} #{person.birthDate}", String.class));
        personCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{person}", Person.class));
        personCreateInput.setConverter(new PersonConverter());
        personCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(personCreateInput);
        
        Message personCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        personCreateInputMessage.setId("personCreateInputMessage");
        personCreateInputMessage.setFor("personCreateInput");
        personCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(personCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid PrincipalBean.populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel userNameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        userNameEditOutput.setFor("userNameEditInput");
        userNameEditOutput.setId("userNameEditOutput");
        userNameEditOutput.setValue("User Name:");
        htmlPanelGrid.getChildren().add(userNameEditOutput);
        
        InputTextarea userNameEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        userNameEditInput.setId("userNameEditInput");
        userNameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.userName}", String.class));
        LengthValidator userNameEditInputValidator = new LengthValidator();
        userNameEditInputValidator.setMinimum(3);
        userNameEditInputValidator.setMaximum(50);
        userNameEditInput.addValidator(userNameEditInputValidator);
        RegexValidator userNameEditInputRegexValidator = new RegexValidator();
        userNameEditInputRegexValidator.setPattern(".+@.+\\.[a-z]+");
        userNameEditInput.addValidator(userNameEditInputRegexValidator);
        userNameEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(userNameEditInput);
        
        Message userNameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        userNameEditInputMessage.setId("userNameEditInputMessage");
        userNameEditInputMessage.setFor("userNameEditInput");
        userNameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(userNameEditInputMessage);
        
        OutputLabel passwordEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        passwordEditOutput.setFor("passwordEditInput");
        passwordEditOutput.setId("passwordEditOutput");
        passwordEditOutput.setValue("Password:");
        htmlPanelGrid.getChildren().add(passwordEditOutput);
        
        InputTextarea passwordEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        passwordEditInput.setId("passwordEditInput");
        passwordEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.password}", String.class));
        LengthValidator passwordEditInputValidator = new LengthValidator();
        passwordEditInputValidator.setMinimum(3);
        passwordEditInputValidator.setMaximum(100);
        passwordEditInput.addValidator(passwordEditInputValidator);
        passwordEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(passwordEditInput);
        
        Message passwordEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        passwordEditInputMessage.setId("passwordEditInputMessage");
        passwordEditInputMessage.setFor("passwordEditInput");
        passwordEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(passwordEditInputMessage);
        
        OutputLabel enabledEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        enabledEditOutput.setFor("enabledEditInput");
        enabledEditOutput.setId("enabledEditOutput");
        enabledEditOutput.setValue("Enabled:");
        htmlPanelGrid.getChildren().add(enabledEditOutput);
        
        SelectBooleanCheckbox enabledEditInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        enabledEditInput.setId("enabledEditInput");
        enabledEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.enabled}", Boolean.class));
        enabledEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(enabledEditInput);
        
        Message enabledEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        enabledEditInputMessage.setId("enabledEditInputMessage");
        enabledEditInputMessage.setFor("enabledEditInput");
        enabledEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(enabledEditInputMessage);
        
        OutputLabel activationKeyEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        activationKeyEditOutput.setFor("activationKeyEditInput");
        activationKeyEditOutput.setId("activationKeyEditOutput");
        activationKeyEditOutput.setValue("Activation Key:");
        htmlPanelGrid.getChildren().add(activationKeyEditOutput);
        
        InputTextarea activationKeyEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        activationKeyEditInput.setId("activationKeyEditInput");
        activationKeyEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.activationKey}", String.class));
        LengthValidator activationKeyEditInputValidator = new LengthValidator();
        activationKeyEditInputValidator.setMinimum(3);
        activationKeyEditInputValidator.setMaximum(100);
        activationKeyEditInput.addValidator(activationKeyEditInputValidator);
        activationKeyEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(activationKeyEditInput);
        
        Message activationKeyEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        activationKeyEditInputMessage.setId("activationKeyEditInputMessage");
        activationKeyEditInputMessage.setFor("activationKeyEditInput");
        activationKeyEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(activationKeyEditInputMessage);
        
        OutputLabel rolesEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        rolesEditOutput.setFor("rolesEditInput");
        rolesEditOutput.setId("rolesEditOutput");
        rolesEditOutput.setValue("Roles:");
        htmlPanelGrid.getChildren().add(rolesEditOutput);
        
        SelectManyMenu rolesEditInput = (SelectManyMenu) application.createComponent(SelectManyMenu.COMPONENT_TYPE);
        rolesEditInput.setId("rolesEditInput");
        rolesEditInput.setConverter(new AuthRoleConverter());
        rolesEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.selectedRoles}", List.class));
        UISelectItems rolesEditInputItems = (UISelectItems) application.createComponent(UISelectItems.COMPONENT_TYPE);
        rolesEditInputItems.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{authRoleBean.allAuthRoles}", List.class));
        rolesEditInput.setRequired(false);
        rolesEditInputItems.setValueExpression("var", expressionFactory.createValueExpression(elContext, "authRole", String.class));
        rolesEditInputItems.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{authRole}", String.class));
        rolesEditInputItems.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{authRole}", AuthRole.class));
        rolesEditInput.getChildren().add(rolesEditInputItems);
        htmlPanelGrid.getChildren().add(rolesEditInput);
        
        Message rolesEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        rolesEditInputMessage.setId("rolesEditInputMessage");
        rolesEditInputMessage.setFor("rolesEditInput");
        rolesEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(rolesEditInputMessage);
        
        OutputLabel personEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        personEditOutput.setFor("personEditInput");
        personEditOutput.setId("personEditOutput");
        personEditOutput.setValue("Person:");
        htmlPanelGrid.getChildren().add(personEditOutput);
        
        AutoComplete personEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        personEditInput.setId("personEditInput");
        personEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.person}", Person.class));
        personEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{principalBean.completePerson}", List.class, new Class[] { String.class }));
        personEditInput.setDropdown(true);
        personEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "person", String.class));
        personEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{person.firstName} #{person.lastName} #{person.lastName2} #{person.birthDate}", String.class));
        personEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{person}", Person.class));
        personEditInput.setConverter(new PersonConverter());
        personEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(personEditInput);
        
        Message personEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        personEditInputMessage.setId("personEditInputMessage");
        personEditInputMessage.setFor("personEditInput");
        personEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(personEditInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid PrincipalBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText userNameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        userNameLabel.setId("userNameLabel");
        userNameLabel.setValue("User Name:");
        htmlPanelGrid.getChildren().add(userNameLabel);
        
        InputTextarea userNameValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        userNameValue.setId("userNameValue");
        userNameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.userName}", String.class));
        userNameValue.setReadonly(true);
        userNameValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(userNameValue);
        
        HtmlOutputText passwordLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        passwordLabel.setId("passwordLabel");
        passwordLabel.setValue("Password:");
        htmlPanelGrid.getChildren().add(passwordLabel);
        
        InputTextarea passwordValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        passwordValue.setId("passwordValue");
        passwordValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.password}", String.class));
        passwordValue.setReadonly(true);
        passwordValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(passwordValue);
        
        HtmlOutputText enabledLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        enabledLabel.setId("enabledLabel");
        enabledLabel.setValue("Enabled:");
        htmlPanelGrid.getChildren().add(enabledLabel);
        
        HtmlOutputText enabledValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        enabledValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.enabled}", String.class));
        htmlPanelGrid.getChildren().add(enabledValue);
        
        HtmlOutputText activationKeyLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        activationKeyLabel.setId("activationKeyLabel");
        activationKeyLabel.setValue("Activation Key:");
        htmlPanelGrid.getChildren().add(activationKeyLabel);
        
        InputTextarea activationKeyValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        activationKeyValue.setId("activationKeyValue");
        activationKeyValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.activationKey}", String.class));
        activationKeyValue.setReadonly(true);
        activationKeyValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(activationKeyValue);
        
        HtmlOutputText rolesLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolesLabel.setId("rolesLabel");
        rolesLabel.setValue("Roles:");
        htmlPanelGrid.getChildren().add(rolesLabel);
        
        SelectManyMenu rolesValue = (SelectManyMenu) application.createComponent(SelectManyMenu.COMPONENT_TYPE);
        rolesValue.setId("rolesValue");
        rolesValue.setConverter(new AuthRoleConverter());
        rolesValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.selectedRoles}", List.class));
        UISelectItems rolesValueItems = (UISelectItems) application.createComponent(UISelectItems.COMPONENT_TYPE);
        rolesValue.setReadonly(true);
        rolesValue.setDisabled(true);
        rolesValueItems.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.roles}", Set.class));
        rolesValueItems.setValueExpression("var", expressionFactory.createValueExpression(elContext, "authRole", String.class));
        rolesValueItems.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{authRole}", String.class));
        rolesValueItems.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{authRole}", AuthRole.class));
        rolesValue.getChildren().add(rolesValueItems);
        htmlPanelGrid.getChildren().add(rolesValue);
        
        HtmlOutputText personLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        personLabel.setId("personLabel");
        personLabel.setValue("Person:");
        htmlPanelGrid.getChildren().add(personLabel);
        
        HtmlOutputText personValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        personValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{principalBean.principal.person}", Person.class));
        personValue.setConverter(new PersonConverter());
        htmlPanelGrid.getChildren().add(personValue);
        
        return htmlPanelGrid;
    }
    
    public Principal PrincipalBean.getPrincipal() {
        if (principal == null) {
            principal = new Principal();
        }
        return principal;
    }
    
    public void PrincipalBean.setPrincipal(Principal principal) {
        this.principal = principal;
    }
    
    public List<AuthRole> PrincipalBean.getSelectedRoles() {
        return selectedRoles;
    }
    
    public void PrincipalBean.setSelectedRoles(List<AuthRole> selectedRoles) {
        if (selectedRoles != null) {
            principal.setRoles(new HashSet<AuthRole>(selectedRoles));
        }
        this.selectedRoles = selectedRoles;
    }
    
    public List<Person> PrincipalBean.completePerson(String query) {
        List<Person> suggestions = new ArrayList<Person>();
        for (Person person : Person.findAllPeople()) {
            String personStr = String.valueOf(person.getFirstName() +  " "  + person.getLastName() +  " "  + person.getLastName2() +  " "  + person.getBirthDate());
            if (personStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(person);
            }
        }
        return suggestions;
    }
    
    public String PrincipalBean.onEdit() {
        if (principal != null && principal.getRoles() != null) {
            selectedRoles = new ArrayList<AuthRole>(principal.getRoles());
        }
        return null;
    }
    
    public boolean PrincipalBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void PrincipalBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String PrincipalBean.displayList() {
        createDialogVisible = false;
        findAllPrincipals();
        return "principal";
    }
    
    public String PrincipalBean.displayCreateDialog() {
        principal = new Principal();
        createDialogVisible = true;
        return "principal";
    }
    
    public String PrincipalBean.persist() {
        String message = "";
        if (principal.getId() != null) {
            principal.merge();
            message = "message_successfully_updated";
        } else {
            principal.persist();
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Principal");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPrincipals();
    }
    
    public String PrincipalBean.delete() {
        principal.remove();
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Principal");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPrincipals();
    }
    
    public void PrincipalBean.reset() {
        principal = null;
        selectedRoles = null;
        createDialogVisible = false;
    }
    
    public void PrincipalBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}
