package golden.horde.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProhibitedValidator implements ConstraintValidator<Prohibited, String> {

	private String prohibitedWord;
	
	@Override
    public void initialize(Prohibited prohibited) {
		prohibitedWord = prohibited.value();
    }
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		/*if (StringUtils.isNullOrEmpty(value))
			return true;*/
		return !value.toLowerCase().contains(prohibitedWord);
	}
}


