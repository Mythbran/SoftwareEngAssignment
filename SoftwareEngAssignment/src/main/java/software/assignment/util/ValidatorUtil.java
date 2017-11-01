/** 
 * @author Oracle
 * This class is used for validating inputs
 * Found in Member.java 
 */

package software.assignment.util;

import javax.validation.*;
public class ValidatorUtil {
  
  private static final ValidatorFactory 
          factory = Validation.buildDefaultValidatorFactory();
  
  public static Validator getValidator(){
      return factory.getValidator();
  }
  
}