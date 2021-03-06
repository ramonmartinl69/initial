package org.myorg.initial.roo.ui.web.jsf.controller.security;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;
import org.myorg.initial.roo.core.domain.reference.SecurityRoleEnum;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.myorg.initial.roo.ui.web.jsf.controller.security.util.MessageFactory;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@Configurable
@ManagedBean(name = "authRoleBean")
@SessionScoped
@RooSerializable
@RooJsfManagedBean(entity = AuthRole.class, beanName = "authRoleBean")
public class AuthRoleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name = "AuthRoles";

	private AuthRole authRole;

	private List<AuthRole> allAuthRoles;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("roleName");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<AuthRole> getAllAuthRoles() {
        return allAuthRoles;
    }

	public void setAllAuthRoles(List<AuthRole> allAuthRoles) {
        this.allAuthRoles = allAuthRoles;
    }

	public String findAllAuthRoles() {
        allAuthRoles = AuthRole.findAllAuthRoles();
        dataVisible = !allAuthRoles.isEmpty();
        return null;
    }

	public boolean isDataVisible() {
        return dataVisible;
    }

	public void setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }

	public HtmlPanelGrid getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }

	public void setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }

	public HtmlPanelGrid getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }

	public void setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }

	public HtmlPanelGrid getViewPanelGrid() {
        return populateViewPanel();
    }

	public void setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }

	public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel authorityCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        authorityCreateOutput.setFor("authorityCreateInput");
        authorityCreateOutput.setId("authorityCreateOutput");
        authorityCreateOutput.setValue("Authority:");
        htmlPanelGrid.getChildren().add(authorityCreateOutput);
        
        AutoComplete authorityCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        authorityCreateInput.setId("authorityCreateInput");
        authorityCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{authRoleBean.authRole.authority}", SecurityRoleEnum.class));
        authorityCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{authRoleBean.completeAuthority}", List.class, new Class[] { String.class }));
        authorityCreateInput.setDropdown(true);
        authorityCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(authorityCreateInput);
        
        Message authorityCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        authorityCreateInputMessage.setId("authorityCreateInputMessage");
        authorityCreateInputMessage.setFor("authorityCreateInput");
        authorityCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(authorityCreateInputMessage);
        
        OutputLabel roleNameCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        roleNameCreateOutput.setFor("roleNameCreateInput");
        roleNameCreateOutput.setId("roleNameCreateOutput");
        roleNameCreateOutput.setValue("Role Name:");
        htmlPanelGrid.getChildren().add(roleNameCreateOutput);
        
        InputTextarea roleNameCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        roleNameCreateInput.setId("roleNameCreateInput");
        roleNameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{authRoleBean.authRole.roleName}", String.class));
        LengthValidator roleNameCreateInputValidator = new LengthValidator();
        roleNameCreateInputValidator.setMinimum(3);
        roleNameCreateInputValidator.setMaximum(100);
        roleNameCreateInput.addValidator(roleNameCreateInputValidator);
        roleNameCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(roleNameCreateInput);
        
        Message roleNameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        roleNameCreateInputMessage.setId("roleNameCreateInputMessage");
        roleNameCreateInputMessage.setFor("roleNameCreateInput");
        roleNameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(roleNameCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel authorityEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        authorityEditOutput.setFor("authorityEditInput");
        authorityEditOutput.setId("authorityEditOutput");
        authorityEditOutput.setValue("Authority:");
        htmlPanelGrid.getChildren().add(authorityEditOutput);
        
        AutoComplete authorityEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        authorityEditInput.setId("authorityEditInput");
        authorityEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{authRoleBean.authRole.authority}", SecurityRoleEnum.class));
        authorityEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{authRoleBean.completeAuthority}", List.class, new Class[] { String.class }));
        authorityEditInput.setDropdown(true);
        authorityEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(authorityEditInput);
        
        Message authorityEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        authorityEditInputMessage.setId("authorityEditInputMessage");
        authorityEditInputMessage.setFor("authorityEditInput");
        authorityEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(authorityEditInputMessage);
        
        OutputLabel roleNameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        roleNameEditOutput.setFor("roleNameEditInput");
        roleNameEditOutput.setId("roleNameEditOutput");
        roleNameEditOutput.setValue("Role Name:");
        htmlPanelGrid.getChildren().add(roleNameEditOutput);
        
        InputTextarea roleNameEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        roleNameEditInput.setId("roleNameEditInput");
        roleNameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{authRoleBean.authRole.roleName}", String.class));
        LengthValidator roleNameEditInputValidator = new LengthValidator();
        roleNameEditInputValidator.setMinimum(3);
        roleNameEditInputValidator.setMaximum(100);
        roleNameEditInput.addValidator(roleNameEditInputValidator);
        roleNameEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(roleNameEditInput);
        
        Message roleNameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        roleNameEditInputMessage.setId("roleNameEditInputMessage");
        roleNameEditInputMessage.setFor("roleNameEditInput");
        roleNameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(roleNameEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText authorityLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        authorityLabel.setId("authorityLabel");
        authorityLabel.setValue("Authority:");
        htmlPanelGrid.getChildren().add(authorityLabel);
        
        HtmlOutputText authorityValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        authorityValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{authRoleBean.authRole.authority}", String.class));
        htmlPanelGrid.getChildren().add(authorityValue);
        
        HtmlOutputText roleNameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        roleNameLabel.setId("roleNameLabel");
        roleNameLabel.setValue("Role Name:");
        htmlPanelGrid.getChildren().add(roleNameLabel);
        
        InputTextarea roleNameValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        roleNameValue.setId("roleNameValue");
        roleNameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{authRoleBean.authRole.roleName}", String.class));
        roleNameValue.setReadonly(true);
        roleNameValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(roleNameValue);
        
        return htmlPanelGrid;
    }

	public AuthRole getAuthRole() {
        if (authRole == null) {
            authRole = new AuthRole();
        }
        return authRole;
    }

	public void setAuthRole(AuthRole authRole) {
        this.authRole = authRole;
    }

	public List<SecurityRoleEnum> completeAuthority(String query) {
        List<SecurityRoleEnum> suggestions = new ArrayList<SecurityRoleEnum>();
        for (SecurityRoleEnum securityRoleEnum : SecurityRoleEnum.values()) {
            if (securityRoleEnum.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(securityRoleEnum);
            }
        }
        return suggestions;
    }

	public String onEdit() {
        return null;
    }

	public boolean isCreateDialogVisible() {
        return createDialogVisible;
    }

	public void setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }

	public String displayList() {
        createDialogVisible = false;
        findAllAuthRoles();
        return "authRole";
    }

	public String displayCreateDialog() {
        authRole = new AuthRole();
        createDialogVisible = true;
        return "authRole";
    }

	public String persist() {
        String message = "";
        if (authRole.getId() != null) {
            authRole.merge();
            message = "message_successfully_updated";
        } else {
            authRole.persist();
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "AuthRole");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllAuthRoles();
    }

	public String delete() {
        authRole.remove();
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "AuthRole");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllAuthRoles();
    }

	public void reset() {
        authRole = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }
}
