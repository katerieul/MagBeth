package magbeth.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import magbeth.core.PieceCodes;
import magbeth.core.Pieces;

// This class is responsible for the appearance of pieces, it stores an instance of piecemodel
// that does the actual work (or rather it should, work in progress).
public class PieceView extends ImageView {
    Pieces.Piece model;
    FieldView parent;
    //TODO: write some functions to get colour, type, etc
    PieceView(Pieces.Piece model, FieldView parent) {
        this.parent=parent;
        this.model=model;
        setFitWidth(100);
        setFitHeight(100);
        setPreserveRatio(true);

        //adding the image
        Image i = null;
        try {
            String filename = PieceCodes.imageName(model.getPieceCode());
            i = new Image(getClass().getResource(filename).toString());
            setImage(i);
        }
        catch(Exception e) {
            System.out.println(e.getCause());
        }
    }
}
