/**
 * Filename:    MalformedFractionException
 * Author:      Shafro Batyrov
 * Date:        7/01/2018
 * Description: Handles incorrect fractions by throwing the MalformedFractionException.
 */
class MalformedFractionException extends Exception{
    MalformedFractionException(String token) {
        super(token);
    }
}