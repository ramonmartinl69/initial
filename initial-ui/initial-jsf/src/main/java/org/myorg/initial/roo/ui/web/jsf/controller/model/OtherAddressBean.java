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
import org.myorg.initial.roo.core.domain.model.OtherAddress;
import org.myorg.initial.roo.core.domain.model.Person;
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
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@Configurable
@ManagedBean(name = "otherAddressBean")
@SessionScoped
@RooSerializable
@RooJsfManagedBean(entity = OtherAddress.class, beanName = "otherAddressBean")
public class OtherAddressBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
    PersonService personService;

	private String name = "OtherAddresses";

	private OtherAddress otherAddress;

	private List<OtherAddress> allOtherAddresses;

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
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<OtherAddress> getAllOtherAddresses() {
        return allOtherAddresses;
    }

	public void setAllOtherAddresses(List<OtherAddress> allOtherAddresses) {
        this.allOtherAddresses = allOtherAddresses;
    }

	public String findAllOtherAddresses() {
        allOtherAddresses = OtherAddress.findAllOtherAddresses();
        dataVisible = !allOtherAddresses.isEmpty();
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
        addressTypeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.addressType}", AddressTypeEnum.class));
        addressTypeCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{otherAddressBean.completeAddressType}", List.class, new Class[] { String.class }));
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
        locationTypeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.locationType}", AddresLocationTypeEnum.class));
        locationTypeCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{otherAddressBean.completeLocationType}", List.class, new Class[] { String.class }));
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
        addressCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.address}", String.class));
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
        countryCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.country}", CountryEnum.class));
        countryCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{otherAddressBean.completeCountry}", List.class, new Class[] { String.class }));
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
        provinceCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.province}", ProvinceEnum.class));
        provinceCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{otherAddressBean.completeProvince}", List.class, new Class[] { String.class }));
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
        postalCodeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.postalCode}", String.class));
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
        populationCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.population}", String.class));
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
        addresNumberCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.addresNumber}", String.class));
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
        personCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.person}", Person.class));
        personCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{otherAddressBean.completePerson}", List.class, new Class[] { String.class }));
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
        
        OutputLabel activeCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        activeCreateOutput.setFor("activeCreateInput");
        activeCreateOutput.setId("activeCreateOutput");
        activeCreateOutput.setValue("Active:");
        htmlPanelGrid.getChildren().add(activeCreateOutput);
        
        SelectBooleanCheckbox activeCreateInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        activeCreateInput.setId("activeCreateInput");
        activeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.active}", Boolean.class));
        activeCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(activeCreateInput);
        
        Message activeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        activeCreateInputMessage.setId("activeCreateInputMessage");
        activeCreateInputMessage.setFor("activeCreateInput");
        activeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(activeCreateInputMessage);
        
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
        addressTypeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.addressType}", AddressTypeEnum.class));
        addressTypeEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{otherAddressBean.completeAddressType}", List.class, new Class[] { String.class }));
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
        locationTypeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.locationType}", AddresLocationTypeEnum.class));
        locationTypeEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{otherAddressBean.completeLocationType}", List.class, new Class[] { String.class }));
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
        addressEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.address}", String.class));
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
        countryEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.country}", CountryEnum.class));
        countryEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{otherAddressBean.completeCountry}", List.class, new Class[] { String.class }));
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
        provinceEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.province}", ProvinceEnum.class));
        provinceEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{otherAddressBean.completeProvince}", List.class, new Class[] { String.class }));
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
        postalCodeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.postalCode}", String.class));
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
        populationEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.population}", String.class));
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
        addresNumberEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.addresNumber}", String.class));
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
        personEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.person}", Person.class));
        personEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{otherAddressBean.completePerson}", List.class, new Class[] { String.class }));
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
        
        OutputLabel activeEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        activeEditOutput.setFor("activeEditInput");
        activeEditOutput.setId("activeEditOutput");
        activeEditOutput.setValue("Active:");
        htmlPanelGrid.getChildren().add(activeEditOutput);
        
        SelectBooleanCheckbox activeEditInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        activeEditInput.setId("activeEditInput");
        activeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.active}", Boolean.class));
        activeEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(activeEditInput);
        
        Message activeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        activeEditInputMessage.setId("activeEditInputMessage");
        activeEditInputMessage.setFor("activeEditInput");
        activeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(activeEditInputMessage);
        
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
        addressTypeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.addressType}", String.class));
        htmlPanelGrid.getChildren().add(addressTypeValue);
        
        HtmlOutputText locationTypeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        locationTypeLabel.setId("locationTypeLabel");
        locationTypeLabel.setValue("Location Type:");
        htmlPanelGrid.getChildren().add(locationTypeLabel);
        
        HtmlOutputText locationTypeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        locationTypeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.locationType}", String.class));
        htmlPanelGrid.getChildren().add(locationTypeValue);
        
        HtmlOutputText addressLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        addressLabel.setId("addressLabel");
        addressLabel.setValue("Address:");
        htmlPanelGrid.getChildren().add(addressLabel);
        
        InputTextarea addressValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        addressValue.setId("addressValue");
        addressValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.address}", String.class));
        addressValue.setReadonly(true);
        addressValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(addressValue);
        
        HtmlOutputText countryLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        countryLabel.setId("countryLabel");
        countryLabel.setValue("Country:");
        htmlPanelGrid.getChildren().add(countryLabel);
        
        HtmlOutputText countryValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        countryValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.country}", String.class));
        htmlPanelGrid.getChildren().add(countryValue);
        
        HtmlOutputText provinceLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        provinceLabel.setId("provinceLabel");
        provinceLabel.setValue("Province:");
        htmlPanelGrid.getChildren().add(provinceLabel);
        
        HtmlOutputText provinceValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        provinceValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.province}", String.class));
        htmlPanelGrid.getChildren().add(provinceValue);
        
        HtmlOutputText postalCodeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        postalCodeLabel.setId("postalCodeLabel");
        postalCodeLabel.setValue("Postal Code:");
        htmlPanelGrid.getChildren().add(postalCodeLabel);
        
        HtmlOutputText postalCodeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        postalCodeValue.setId("postalCodeValue");
        postalCodeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.postalCode}", String.class));
        htmlPanelGrid.getChildren().add(postalCodeValue);
        
        HtmlOutputText populationLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        populationLabel.setId("populationLabel");
        populationLabel.setValue("Population:");
        htmlPanelGrid.getChildren().add(populationLabel);
        
        InputTextarea populationValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        populationValue.setId("populationValue");
        populationValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.population}", String.class));
        populationValue.setReadonly(true);
        populationValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(populationValue);
        
        HtmlOutputText addresNumberLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        addresNumberLabel.setId("addresNumberLabel");
        addresNumberLabel.setValue("Addres Number:");
        htmlPanelGrid.getChildren().add(addresNumberLabel);
        
        InputTextarea addresNumberValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        addresNumberValue.setId("addresNumberValue");
        addresNumberValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.addresNumber}", String.class));
        addresNumberValue.setReadonly(true);
        addresNumberValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(addresNumberValue);
        
        HtmlOutputText personLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        personLabel.setId("personLabel");
        personLabel.setValue("Person:");
        htmlPanelGrid.getChildren().add(personLabel);
        
        HtmlOutputText personValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        personValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.person}", Person.class));
        personValue.setConverter(new PersonConverter());
        htmlPanelGrid.getChildren().add(personValue);
        
        HtmlOutputText activeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        activeLabel.setId("activeLabel");
        activeLabel.setValue("Active:");
        htmlPanelGrid.getChildren().add(activeLabel);
        
        HtmlOutputText activeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        activeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{otherAddressBean.otherAddress.active}", String.class));
        htmlPanelGrid.getChildren().add(activeValue);
        
        return htmlPanelGrid;
    }

	public OtherAddress getOtherAddress() {
        if (otherAddress == null) {
            otherAddress = new OtherAddress();
        }
        return otherAddress;
    }

	public void setOtherAddress(OtherAddress otherAddress) {
        this.otherAddress = otherAddress;
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
        findAllOtherAddresses();
        return "otherAddress";
    }

	public String displayCreateDialog() {
        otherAddress = new OtherAddress();
        createDialogVisible = true;
        return "otherAddress";
    }

	public String persist() {
        String message = "";
        if (otherAddress.getId() != null) {
            otherAddress.merge();
            message = "message_successfully_updated";
        } else {
            otherAddress.persist();
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "OtherAddress");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllOtherAddresses();
    }

	public String delete() {
        otherAddress.remove();
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "OtherAddress");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllOtherAddresses();
    }

	public void reset() {
        otherAddress = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }
}
