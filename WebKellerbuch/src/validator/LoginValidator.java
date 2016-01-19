package validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
@FacesValidator(value="loginValidator")
public class LoginValidator implements Validator
{
	private FacesMessage message;
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value)
			throws ValidatorException
	{
		String unsername = String.valueOf(value);
		
		boolean user = true;
	}

}
