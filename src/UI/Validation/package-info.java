/**
 * UI package that is needed to display the results of the Validation process.
 * Displays are controlled by the MasterValidator, which prompt the AllFilesDisplay to update individual status icons showing whether or not certain XML files have been marked as valid.
 * AllFilesDisplay serves as the reference point for the controller, which then delegates method calls to IndividualFileDisplays.
 * Uses enumerated types with keys mapping to IconBundles to access icons that are appropriate for the display.
 * @author Eric Doppelt
 */
package UI.Validation;