package org.myorg.initial.roo.ui.web.jsf.controller.model;
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
import javax.faces.validator.RegexValidator;
import org.myorg.initial.roo.core.domain.model.Person;
import org.myorg.initial.roo.core.domain.model.WorkAddress;
import org.myorg.initial.roo.core.domain.reference.AddresLocationTypeEnum;
import org.myorg.initial.roo.core.domain.reference.AddressTypeEnum;
import org.myorg.initial.roo.core.domain.reference.CountryEnum;
import org.myorg.initial.roo.core.domain.reference.ProvinceEnum;
import org.myorg.initial.roo.core.service.model.PersonService;
import org.myorg.initial.roo.ui.web.jsf.controller.model.converter.PersonConverter;
import org.myorg.initial.roo.ui.web.jsf.controller.model.util.MessageFactory;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@Configurable
@ManagedBean(name = "workAddressBean")
@SessionScoped
@RooSerializable
@RooJsfManagedBean(entity = WorkAddress.class, beanName = "workAddressBean")
public class WorkAddressBean implements Serializable {

	@Autowired
    PersonService personService;

	private String name = "WorkAddresses";

	private WorkAddress workAddress;

	private List<WorkAddress> allWorkAddresses;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("address");
        columns.add("postalCode");
        columns.add("population");
        columns.add("addresNumber");
        columns.add("enterpriseName");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<WorkAddress> getAllWorkAddresses() {
        return allWorkAddresses;
    }

	public void setAllWorkAddresses(List<WorkAddress> allWorkAddresses) {
        this.allWorkAddresses = allWorkAddresses;
    }

	public String findAllWorkAddresses() {
        allWorkAddresses = WorkAddress.findAllWorkAddresses();
        dataVisible = !allWorkAddresses.isEmpty();
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
        
        OutputLabel addressTypeCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        addressTypeCreateOutput.setFor("addressTypeCreateInput");
        addressTypeCreateOutput.setId("addressTypeCreateOutput");
        addressTypeCreateOutput.setValue("Address Type:");
        htmlPanelGrid.getChildren().add(addressTypeCreateOutput);
        
        AutoComplete addressTypeCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        addressTypeCreateInput.setId("addressTypeCreateInput");
        addressTypeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.addressType}", AddressTypeEnum.class));
        addressTypeCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{workAddressBean.completeAddressType}", List.class, new Class[] { String.class }));
        addressTypeCreateInput.setDropdown(true);
        addressTypeCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(addressTypeCreateInput);
        
        Message addressTypeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        addressTypeCreateInputMessage.setId("addressTypeCreateInputMessage");
        addressTypeCreateInputMessage.setFor("addressTypeCreateInput");
        addressTypeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(addressTypeCreateInputMessage);
        
        OutputLabel locationTypeCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        locationTypeCreateOutput.setFor("locationTypeCreateInput");
        locationTypeCreateOutput.setId("locationTypeCreateOutput");
        locationTypeCreateOutput.setValue("Location Type:");
        htmlPanelGrid.getChildren().add(locationTypeCreateOutput);
        
        AutoComplete locationTypeCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        locationTypeCreateInput.setId("locationTypeCreateInput");
        locationTypeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.locationType}", AddresLocationTypeEnum.class));
        locationTypeCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{workAddressBean.completeLocationType}", List.class, new Class[] { String.class }));
        locationTypeCreateInput.setDropdown(true);
        locationTypeCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(locationTypeCreateInput);
        
        Message locationTypeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        locationTypeCreateInputMessage.setId("locationTypeCreateInputMessage");
        locationTypeCreateInputMessage.setFor("locationTypeCreateInput");
        locationTypeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(locationTypeCreateInputMessage);
        
        OutputLabel addressCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        addressCreateOutput.setFor("addressCreateInput");
        addressCreateOutput.setId("addressCreateOutput");
        addressCreateOutput.setValue("Address:");
        htmlPanelGrid.getChildren().add(addressCreateOutput);
        
        InputTextarea addressCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        addressCreateInput.setId("addressCreateInput");
        addressCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.address}", String.class));
        LengthValidator addressCreateInputValidator = new LengthValidator();
        addressCreateInputValidator.setMinimum(3);
        addressCreateInputValidator.setMaximum(250);
        addressCreateInput.addValidator(addressCreateInputValidator);
        addressCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(addressCreateInput);
        
        Message addressCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        addressCreateInputMessage.setId("addressCreateInputMessage");
        addressCreateInputMessage.setFor("addressCreateInput");
        addressCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(addressCreateInputMessage);
        
        OutputLabel countryCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        countryCreateOutput.setFor("countryCreateInput");
        countryCreateOutput.setId("countryCreateOutput");
        countryCreateOutput.setValue("Country:");
        htmlPanelGrid.getChildren().add(countryCreateOutput);
        
        AutoComplete countryCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        countryCreateInput.setId("countryCreateInput");
        countryCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.country}", CountryEnum.class));
        countryCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{workAddressBean.completeCountry}", List.class, new Class[] { String.class }));
        countryCreateInput.setDropdown(true);
        countryCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(countryCreateInput);
        
        Message countryCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        countryCreateInputMessage.setId("countryCreateInputMessage");
        countryCreateInputMessage.setFor("countryCreateInput");
        countryCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(countryCreateInputMessage);
        
        OutputLabel provinceCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        provinceCreateOutput.setFor("provinceCreateInput");
        provinceCreateOutput.setId("provinceCreateOutput");
        provinceCreateOutput.setValue("Province:");
        htmlPanelGrid.getChildren().add(provinceCreateOutput);
        
        AutoComplete provinceCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        provinceCreateInput.setId("provinceCreateInput");
        provinceCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.province}", ProvinceEnum.class));
        provinceCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{workAddressBean.completeProvince}", List.class, new Class[] { String.class }));
        provinceCreateInput.setDropdown(true);
        provinceCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(provinceCreateInput);
        
        Message provinceCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        provinceCreateInputMessage.setId("provinceCreateInputMessage");
        provinceCreateInputMessage.setFor("provinceCreateInput");
        provinceCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(provinceCreateInputMessage);
        
        OutputLabel postalCodeCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        postalCodeCreateOutput.setFor("postalCodeCreateInput");
        postalCodeCreateOutput.setId("postalCodeCreateOutput");
        postalCodeCreateOutput.setValue("Postal Code:");
        htmlPanelGrid.getChildren().add(postalCodeCreateOutput);
        
        InputText postalCodeCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        postalCodeCreateInput.setId("postalCodeCreateInput");
        postalCodeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.postalCode}", String.class));
        LengthValidator postalCodeCreateInputValidator = new LengthValidator();
        postalCodeCreateInputValidator.setMinimum(3);
        postalCodeCreateInputValidator.setMaximum(10);
        postalCodeCreateInput.addValidator(postalCodeCreateInputValidator);
        RegexValidator postalCodeCreateInputRegexValidator = new RegexValidator();
        postalCodeCreateInputRegexValidator.setPattern("^[0-9]*$");
        postalCodeCreateInput.addValidator(postalCodeCreateInputRegexValidator);
        postalCodeCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(postalCodeCreateInput);
        
        Message postalCodeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        postalCodeCreateInputMessage.setId("postalCodeCreateInputMessage");
        postalCodeCreateInputMessage.setFor("postalCodeCreateInput");
        postalCodeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(postalCodeCreateInputMessage);
        
        OutputLabel populationCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        populationCreateOutput.setFor("populationCreateInput");
        populationCreateOutput.setId("populationCreateOutput");
        populationCreateOutput.setValue("Population:");
        htmlPanelGrid.getChildren().add(populationCreateOutput);
        
        InputTextarea populationCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        populationCreateInput.setId("populationCreateInput");
        populationCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.population}", String.class));
        LengthValidator populationCreateInputValidator = new LengthValidator();
        populationCreateInputValidator.setMinimum(3);
        populationCreateInputValidator.setMaximum(250);
        populationCreateInput.addValidator(populationCreateInputValidator);
        populationCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(populationCreateInput);
        
        Message populationCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        populationCreateInputMessage.setId("populationCreateInputMessage");
        populationCreateInputMessage.setFor("populationCreateInput");
        populationCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(populationCreateInputMessage);
        
        OutputLabel addresNumberCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        addresNumberCreateOutput.setFor("addresNumberCreateInput");
        addresNumberCreateOutput.setId("addresNumberCreateOutput");
        addresNumberCreateOutput.setValue("Addres Number:");
        htmlPanelGrid.getChildren().add(addresNumberCreateOutput);
        
        InputTextarea addresNumberCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        addresNumberCreateInput.setId("addresNumberCreateInput");
        addresNumberCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.addresNumber}", String.class));
        LengthValidator addresNumberCreateInputValidator = new LengthValidator();
        addresNumberCreateInputValidator.setMinimum(1);
        addresNumberCreateInputValidator.setMaximum(250);
        addresNumberCreateInput.addValidator(addresNumberCreateInputValidator);
        addresNumberCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(addresNumberCreateInput);
        
        Message addresNumberCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        addresNumberCreateInputMessage.setId("addresNumberCreateInputMessage");
        addresNumberCreateInputMessage.setFor("addresNumberCreateInput");
        addresNumberCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(addresNumberCreateInputMessage);
        
        OutputLabel personCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        personCreateOutput.setFor("personCreateInput");
        personCreateOutput.setId("personCreateOutput");
        personCreateOutput.setValue("Person:");
        htmlPanelGrid.getChildren().add(personCreateOutput);
        
        AutoComplete personCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        personCreateInput.setId("personCreateInput");
        personCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.person}", Person.class));
        personCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{workAddressBean.completePerson}", List.class, new Class[] { String.class }));
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
        
        OutputLabel enterpriseNameCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        enterpriseNameCreateOutput.setFor("enterpriseNameCreateInput");
        enterpriseNameCreateOutput.setId("enterpriseNameCreateOutput");
        enterpriseNameCreateOutput.setValue("Enterprise Name:");
        htmlPanelGrid.getChildren().add(enterpriseNameCreateOutput);
        
        InputTextarea enterpriseNameCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        enterpriseNameCreateInput.setId("enterpriseNameCreateInput");
        enterpriseNameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.enterpriseName}", String.class));
        LengthValidator enterpriseNameCreateInputValidator = new LengthValidator();
        enterpriseNameCreateInputValidator.setMinimum(3);
        enterpriseNameCreateInputValidator.setMaximum(250);
        enterpriseNameCreateInput.addValidator(enterpriseNameCreateInputValidator);
        enterpriseNameCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(enterpriseNameCreateInput);
        
        Message enterpriseNameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        enterpriseNameCreateInputMessage.setId("enterpriseNameCreateInputMessage");
        enterpriseNameCreateInputMessage.setFor("enterpriseNameCreateInput");
        enterpriseNameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(enterpriseNameCreateInputMessage);
        
        OutputLabel postalAddressCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        postalAddressCreateOutput.setFor("postalAddressCreateInput");
        postalAddressCreateOutput.setId("postalAddressCreateOutput");
        postalAddressCreateOutput.setValue("Postal Address:");
        htmlPanelGrid.getChildren().add(postalAddressCreateOutput);
        
        InputTextarea postalAddressCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        postalAddressCreateInput.setId("postalAddressCreateInput");
        postalAddressCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.postalAddress}", String.class));
        LengthValidator postalAddressCreateInputValidator = new LengthValidator();
        postalAddressCreateInputValidator.setMinimum(3);
        postalAddressCreateInputValidator.setMaximum(250);
        postalAddressCreateInput.addValidator(postalAddressCreateInputValidator);
        postalAddressCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(postalAddressCreateInput);
        
        Message postalAddressCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        postalAddressCreateInputMessage.setId("postalAddressCreateInputMessage");
        postalAddressCreateInputMessage.setFor("postalAddressCreateInput");
        postalAddressCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(postalAddressCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel addressTypeEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        addressTypeEditOutput.setFor("addressTypeEditInput");
        addressTypeEditOutput.setId("addressTypeEditOutput");
        addressTypeEditOutput.setValue("Address Type:");
        htmlPanelGrid.getChildren().add(addressTypeEditOutput);
        
        AutoComplete addressTypeEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        addressTypeEditInput.setId("addressTypeEditInput");
        addressTypeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.addressType}", AddressTypeEnum.class));
        addressTypeEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{workAddressBean.completeAddressType}", List.class, new Class[] { String.class }));
        addressTypeEditInput.setDropdown(true);
        addressTypeEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(addressTypeEditInput);
        
        Message addressTypeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        addressTypeEditInputMessage.setId("addressTypeEditInputMessage");
        addressTypeEditInputMessage.setFor("addressTypeEditInput");
        addressTypeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(addressTypeEditInputMessage);
        
        OutputLabel locationTypeEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        locationTypeEditOutput.setFor("locationTypeEditInput");
        locationTypeEditOutput.setId("locationTypeEditOutput");
        locationTypeEditOutput.setValue("Location Type:");
        htmlPanelGrid.getChildren().add(locationTypeEditOutput);
        
        AutoComplete locationTypeEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        locationTypeEditInput.setId("locationTypeEditInput");
        locationTypeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.locationType}", AddresLocationTypeEnum.class));
        locationTypeEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{workAddressBean.completeLocationType}", List.class, new Class[] { String.class }));
        locationTypeEditInput.setDropdown(true);
        locationTypeEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(locationTypeEditInput);
        
        Message locationTypeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        locationTypeEditInputMessage.setId("locationTypeEditInputMessage");
        locationTypeEditInputMessage.setFor("locationTypeEditInput");
        locationTypeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(locationTypeEditInputMessage);
        
        OutputLabel addressEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        addressEditOutput.setFor("addressEditInput");
        addressEditOutput.setId("addressEditOutput");
        addressEditOutput.setValue("Address:");
        htmlPanelGrid.getChildren().add(addressEditOutput);
        
        InputTextarea addressEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        addressEditInput.setId("addressEditInput");
        addressEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.address}", String.class));
        LengthValidator addressEditInputValidator = new LengthValidator();
        addressEditInputValidator.setMinimum(3);
        addressEditInputValidator.setMaximum(250);
        addressEditInput.addValidator(addressEditInputValidator);
        addressEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(addressEditInput);
        
        Message addressEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        addressEditInputMessage.setId("addressEditInputMessage");
        addressEditInputMessage.setFor("addressEditInput");
        addressEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(addressEditInputMessage);
        
        OutputLabel countryEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        countryEditOutput.setFor("countryEditInput");
        countryEditOutput.setId("countryEditOutput");
        countryEditOutput.setValue("Country:");
        htmlPanelGrid.getChildren().add(countryEditOutput);
        
        AutoComplete countryEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        countryEditInput.setId("countryEditInput");
        countryEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.country}", CountryEnum.class));
        countryEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{workAddressBean.completeCountry}", List.class, new Class[] { String.class }));
        countryEditInput.setDropdown(true);
        countryEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(countryEditInput);
        
        Message countryEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        countryEditInputMessage.setId("countryEditInputMessage");
        countryEditInputMessage.setFor("countryEditInput");
        countryEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(countryEditInputMessage);
        
        OutputLabel provinceEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        provinceEditOutput.setFor("provinceEditInput");
        provinceEditOutput.setId("provinceEditOutput");
        provinceEditOutput.setValue("Province:");
        htmlPanelGrid.getChildren().add(provinceEditOutput);
        
        AutoComplete provinceEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        provinceEditInput.setId("provinceEditInput");
        provinceEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.province}", ProvinceEnum.class));
        provinceEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{workAddressBean.completeProvince}", List.class, new Class[] { String.class }));
        provinceEditInput.setDropdown(true);
        provinceEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(provinceEditInput);
        
        Message provinceEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        provinceEditInputMessage.setId("provinceEditInputMessage");
        provinceEditInputMessage.setFor("provinceEditInput");
        provinceEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(provinceEditInputMessage);
        
        OutputLabel postalCodeEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        postalCodeEditOutput.setFor("postalCodeEditInput");
        postalCodeEditOutput.setId("postalCodeEditOutput");
        postalCodeEditOutput.setValue("Postal Code:");
        htmlPanelGrid.getChildren().add(postalCodeEditOutput);
        
        InputText postalCodeEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        postalCodeEditInput.setId("postalCodeEditInput");
        postalCodeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.postalCode}", String.class));
        LengthValidator postalCodeEditInputValidator = new LengthValidator();
        postalCodeEditInputValidator.setMinimum(3);
        postalCodeEditInputValidator.setMaximum(10);
        postalCodeEditInput.addValidator(postalCodeEditInputValidator);
        RegexValidator postalCodeEditInputRegexValidator = new RegexValidator();
        postalCodeEditInputRegexValidator.setPattern("^[0-9]*$");
        postalCodeEditInput.addValidator(postalCodeEditInputRegexValidator);
        postalCodeEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(postalCodeEditInput);
        
        Message postalCodeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        postalCodeEditInputMessage.setId("postalCodeEditInputMessage");
        postalCodeEditInputMessage.setFor("postalCodeEditInput");
        postalCodeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(postalCodeEditInputMessage);
        
        OutputLabel populationEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        populationEditOutput.setFor("populationEditInput");
        populationEditOutput.setId("populationEditOutput");
        populationEditOutput.setValue("Population:");
        htmlPanelGrid.getChildren().add(populationEditOutput);
        
        InputTextarea populationEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        populationEditInput.setId("populationEditInput");
        populationEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.population}", String.class));
        LengthValidator populationEditInputValidator = new LengthValidator();
        populationEditInputValidator.setMinimum(3);
        populationEditInputValidator.setMaximum(250);
        populationEditInput.addValidator(populationEditInputValidator);
        populationEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(populationEditInput);
        
        Message populationEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        populationEditInputMessage.setId("populationEditInputMessage");
        populationEditInputMessage.setFor("populationEditInput");
        populationEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(populationEditInputMessage);
        
        OutputLabel addresNumberEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        addresNumberEditOutput.setFor("addresNumberEditInput");
        addresNumberEditOutput.setId("addresNumberEditOutput");
        addresNumberEditOutput.setValue("Addres Number:");
        htmlPanelGrid.getChildren().add(addresNumberEditOutput);
        
        InputTextarea addresNumberEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        addresNumberEditInput.setId("addresNumberEditInput");
        addresNumberEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.addresNumber}", String.class));
        LengthValidator addresNumberEditInputValidator = new LengthValidator();
        addresNumberEditInputValidator.setMinimum(1);
        addresNumberEditInputValidator.setMaximum(250);
        addresNumberEditInput.addValidator(addresNumberEditInputValidator);
        addresNumberEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(addresNumberEditInput);
        
        Message addresNumberEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        addresNumberEditInputMessage.setId("addresNumberEditInputMessage");
        addresNumberEditInputMessage.setFor("addresNumberEditInput");
        addresNumberEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(addresNumberEditInputMessage);
        
        OutputLabel personEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        personEditOutput.setFor("personEditInput");
        personEditOutput.setId("personEditOutput");
        personEditOutput.setValue("Person:");
        htmlPanelGrid.getChildren().add(personEditOutput);
        
        AutoComplete personEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        personEditInput.setId("personEditInput");
        personEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.person}", Person.class));
        personEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{workAddressBean.completePerson}", List.class, new Class[] { String.class }));
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
        
        OutputLabel enterpriseNameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        enterpriseNameEditOutput.setFor("enterpriseNameEditInput");
        enterpriseNameEditOutput.setId("enterpriseNameEditOutput");
        enterpriseNameEditOutput.setValue("Enterprise Name:");
        htmlPanelGrid.getChildren().add(enterpriseNameEditOutput);
        
        InputTextarea enterpriseNameEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        enterpriseNameEditInput.setId("enterpriseNameEditInput");
        enterpriseNameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.enterpriseName}", String.class));
        LengthValidator enterpriseNameEditInputValidator = new LengthValidator();
        enterpriseNameEditInputValidator.setMinimum(3);
        enterpriseNameEditInputValidator.setMaximum(250);
        enterpriseNameEditInput.addValidator(enterpriseNameEditInputValidator);
        enterpriseNameEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(enterpriseNameEditInput);
        
        Message enterpriseNameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        enterpriseNameEditInputMessage.setId("enterpriseNameEditInputMessage");
        enterpriseNameEditInputMessage.setFor("enterpriseNameEditInput");
        enterpriseNameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(enterpriseNameEditInputMessage);
        
        OutputLabel postalAddressEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        postalAddressEditOutput.setFor("postalAddressEditInput");
        postalAddressEditOutput.setId("postalAddressEditOutput");
        postalAddressEditOutput.setValue("Postal Address:");
        htmlPanelGrid.getChildren().add(postalAddressEditOutput);
        
        InputTextarea postalAddressEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        postalAddressEditInput.setId("postalAddressEditInput");
        postalAddressEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.postalAddress}", String.class));
        LengthValidator postalAddressEditInputValidator = new LengthValidator();
        postalAddressEditInputValidator.setMinimum(3);
        postalAddressEditInputValidator.setMaximum(250);
        postalAddressEditInput.addValidator(postalAddressEditInputValidator);
        postalAddressEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(postalAddressEditInput);
        
        Message postalAddressEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        postalAddressEditInputMessage.setId("postalAddressEditInputMessage");
        postalAddressEditInputMessage.setFor("postalAddressEditInput");
        postalAddressEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(postalAddressEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText addressTypeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        addressTypeLabel.setId("addressTypeLabel");
        addressTypeLabel.setValue("Address Type:");
        htmlPanelGrid.getChildren().add(addressTypeLabel);
        
        HtmlOutputText addressTypeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        addressTypeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.addressType}", String.class));
        htmlPanelGrid.getChildren().add(addressTypeValue);
        
        HtmlOutputText locationTypeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        locationTypeLabel.setId("locationTypeLabel");
        locationTypeLabel.setValue("Location Type:");
        htmlPanelGrid.getChildren().add(locationTypeLabel);
        
        HtmlOutputText locationTypeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        locationTypeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.locationType}", String.class));
        htmlPanelGrid.getChildren().add(locationTypeValue);
        
        HtmlOutputText addressLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        addressLabel.setId("addressLabel");
        addressLabel.setValue("Address:");
        htmlPanelGrid.getChildren().add(addressLabel);
        
        InputTextarea addressValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        addressValue.setId("addressValue");
        addressValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.address}", String.class));
        addressValue.setReadonly(true);
        addressValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(addressValue);
        
        HtmlOutputText countryLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        countryLabel.setId("countryLabel");
        countryLabel.setValue("Country:");
        htmlPanelGrid.getChildren().add(countryLabel);
        
        HtmlOutputText countryValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        countryValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.country}", String.class));
        htmlPanelGrid.getChildren().add(countryValue);
        
        HtmlOutputText provinceLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        provinceLabel.setId("provinceLabel");
        provinceLabel.setValue("Province:");
        htmlPanelGrid.getChildren().add(provinceLabel);
        
        HtmlOutputText provinceValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        provinceValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.province}", String.class));
        htmlPanelGrid.getChildren().add(provinceValue);
        
        HtmlOutputText postalCodeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        postalCodeLabel.setId("postalCodeLabel");
        postalCodeLabel.setValue("Postal Code:");
        htmlPanelGrid.getChildren().add(postalCodeLabel);
        
        HtmlOutputText postalCodeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        postalCodeValue.setId("postalCodeValue");
        postalCodeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.postalCode}", String.class));
        htmlPanelGrid.getChildren().add(postalCodeValue);
        
        HtmlOutputText populationLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        populationLabel.setId("populationLabel");
        populationLabel.setValue("Population:");
        htmlPanelGrid.getChildren().add(populationLabel);
        
        InputTextarea populationValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        populationValue.setId("populationValue");
        populationValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.population}", String.class));
        populationValue.setReadonly(true);
        populationValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(populationValue);
        
        HtmlOutputText addresNumberLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        addresNumberLabel.setId("addresNumberLabel");
        addresNumberLabel.setValue("Addres Number:");
        htmlPanelGrid.getChildren().add(addresNumberLabel);
        
        InputTextarea addresNumberValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        addresNumberValue.setId("addresNumberValue");
        addresNumberValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.addresNumber}", String.class));
        addresNumberValue.setReadonly(true);
        addresNumberValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(addresNumberValue);
        
        HtmlOutputText personLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        personLabel.setId("personLabel");
        personLabel.setValue("Person:");
        htmlPanelGrid.getChildren().add(personLabel);
        
        HtmlOutputText personValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        personValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.person}", Person.class));
        personValue.setConverter(new PersonConverter());
        htmlPanelGrid.getChildren().add(personValue);
        
        HtmlOutputText enterpriseNameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        enterpriseNameLabel.setId("enterpriseNameLabel");
        enterpriseNameLabel.setValue("Enterprise Name:");
        htmlPanelGrid.getChildren().add(enterpriseNameLabel);
        
        InputTextarea enterpriseNameValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        enterpriseNameValue.setId("enterpriseNameValue");
        enterpriseNameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.enterpriseName}", String.class));
        enterpriseNameValue.setReadonly(true);
        enterpriseNameValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(enterpriseNameValue);
        
        HtmlOutputText postalAddressLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        postalAddressLabel.setId("postalAddressLabel");
        postalAddressLabel.setValue("Postal Address:");
        htmlPanelGrid.getChildren().add(postalAddressLabel);
        
        InputTextarea postalAddressValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        postalAddressValue.setId("postalAddressValue");
        postalAddressValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{workAddressBean.workAddress.postalAddress}", String.class));
        postalAddressValue.setReadonly(true);
        postalAddressValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(postalAddressValue);
        
        return htmlPanelGrid;
    }

	public WorkAddress getWorkAddress() {
        if (workAddress == null) {
            workAddress = new WorkAddress();
        }
        return workAddress;
    }

	public void setWorkAddress(WorkAddress workAddress) {
        this.workAddress = workAddress;
    }

	public List<AddressTypeEnum> completeAddressType(String query) {
        List<AddressTypeEnum> suggestions = new ArrayList<AddressTypeEnum>();
        for (AddressTypeEnum addressTypeEnum : AddressTypeEnum.values()) {
            if (addressTypeEnum.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(addressTypeEnum);
            }
        }
        return suggestions;
    }

	public List<AddresLocationTypeEnum> completeLocationType(String query) {
        List<AddresLocationTypeEnum> suggestions = new ArrayList<AddresLocationTypeEnum>();
        for (AddresLocationTypeEnum addresLocationTypeEnum : AddresLocationTypeEnum.values()) {
            if (addresLocationTypeEnum.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(addresLocationTypeEnum);
            }
        }
        return suggestions;
    }

	public List<CountryEnum> completeCountry(String query) {
        List<CountryEnum> suggestions = new ArrayList<CountryEnum>();
        for (CountryEnum countryEnum : CountryEnum.values()) {
            if (countryEnum.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(countryEnum);
            }
        }
        return suggestions;
    }

	public List<ProvinceEnum> completeProvince(String query) {
        List<ProvinceEnum> suggestions = new ArrayList<ProvinceEnum>();
        for (ProvinceEnum provinceEnum : ProvinceEnum.values()) {
            if (provinceEnum.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(provinceEnum);
            }
        }
        return suggestions;
    }

	public List<Person> completePerson(String query) {
        List<Person> suggestions = new ArrayList<Person>();
        for (Person person : personService.findAllPeople()) {
            String personStr = String.valueOf(person.getFirstName() +  " "  + person.getLastName() +  " "  + person.getLastName2() +  " "  + person.getBirthDate());
            if (personStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(person);
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
        findAllWorkAddresses();
        return "workAddress";
    }

	public String displayCreateDialog() {
        workAddress = new WorkAddress();
        createDialogVisible = true;
        return "workAddress";
    }

	public String persist() {
        String message = "";
        if (workAddress.getId() != null) {
            workAddress.merge();
            message = "message_successfully_updated";
        } else {
            workAddress.persist();
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "WorkAddress");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllWorkAddresses();
    }

	public String delete() {
        workAddress.remove();
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "WorkAddress");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllWorkAddresses();
    }

	public void reset() {
        workAddress = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	private static final long serialVersionUID = 1L;
}
