package magbeth.core;
import java.util.Map;

//  This class is responsible for processing different types of notation
// We want to store pieces on the board as ints, 'cause ints are nice. We want to store the type and the colour of each piece,
// we are going to use bitwise or to achieve that

//TODO: Naming here seriously sucks. We should decide on what's a "code", what's a "value", what's a "piece"
// because right now it seems totally random (yes, it was me who wrote the code, I do realise that)

public class PieceCodes {
    // Ah yess, no enums in java

    public static final int empty=0;
    public static final int pawn=1;
    public static final int knight=2;
    public static final int bishop=3;
    public static final int rook=4;
    public static final int queen=5;
    public static final int king=6;

    public static final int white=0;
    public static final int black=8;

    //TODO: change into BiMap or something, requires reading into documentation and right now I really don't want to

    public static final Map<Character, Integer> FENCODES_TO_CODES = Map.of(
            'p', pawn,
            'n', knight,
            'b', bishop,
            'r', rook,
            'q', queen,
            'k', king
    );

    // yes, this could be an array, but I'm still hoping that we can merge it with the previous^ thing elegantly
    public static final Map<Integer, Character> CODES_TO_FENCODES = Map.of(
            pawn,'p',
            knight,'n',
            bishop,'b',
            rook,'r',
            queen,'q',
            king,'k'
    );

    //gets character code and returns its number
    public static int FENcodeToCode(char a) {
        // Assumes the symbol is valid
        int result = Character.isUpperCase(a) ? white : black;
        if(! FENCODES_TO_CODES.containsKey(Character.toLowerCase(a))) {
            // TODO: (what?)
        }
        result |= FENCODES_TO_CODES.get(Character.toLowerCase(a));
        return result;
    }

    //returns symbol without considering the colour (is white by default)
    public static char codeToFENcode(int a) {
        // Assumes the symbol is valid
        if(!CODES_TO_FENCODES.containsKey(a)) {
            // TODO: (what?)
        }
        return CODES_TO_FENCODES.get(a);
    }

    // Returns an image corresponding to the code
    // We should definitely reconsider the mechanism of computing filenames for images
    // because I have a strong suspicion that it might not be the correct way to handle it.

    public static String imageName(int code) {
        return "/images/"+ codeToFENcode(getType(code))+getColour(code)+".png";
    }

    public static int getType(int code) {
        return code&(0b111);
    }
    public static int getColour(int code) {
        return code&(0b1000);
    }
    public static boolean isWhite(int code) { return getColour(code)==white; }
    public static boolean isBlack(int code) { return !isWhite(code); }
    public static boolean isEmpty(int code) { return code==empty; }
    public static boolean sameColour(int code1, int code2) { return getColour(code1)==getColour(code2); }
}
