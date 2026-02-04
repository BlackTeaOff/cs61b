package capers;

import java.io.File;
import java.io.IOException;

import static capers.Utils.*;

/** A repository for Capers 
 * @author BlackTea
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 *
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = Utils.join(".capers");

    static final File STORY_FILE = Utils.join(CAPERS_FOLDER, "story");

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        if (!CAPERS_FOLDER.exists()) {
            if (!CAPERS_FOLDER.mkdirs()) {
                throw new RuntimeException("Cannot create " + CAPERS_FOLDER);
            }
        }
        if (!Dog.DOG_FOLDER.exists()) {
            if (!Dog.DOG_FOLDER.mkdirs()) {
                throw new RuntimeException("Cannot create " + Dog.DOG_FOLDER);
            }
        }
        if (!STORY_FILE.exists()) {
            try {
                STORY_FILE.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Cannot create " + STORY_FILE, e);
            }
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        String oldStr = Utils.readContentsAsString(STORY_FILE);
        String newStr = oldStr + text + "\n";
        Utils.writeContents(STORY_FILE,  newStr);
        System.out.print(newStr);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog d = new Dog(name, breed, age);
        d.saveDog();
        System.out.println(d);
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog d = Dog.fromFile(name);
        if (d == null) {
            return;
        }
        d.haveBirthday();
        d.saveDog();
    }
}
