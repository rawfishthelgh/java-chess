package chess.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RowPiecesTest {
    private RowPieces rowPieces;
    
    @BeforeEach
    void setUp() {
        rowPieces = new RowPieces(8);
    }
    
    @Test
    void 한_행마다_8개의_기물을_저장한다() {
        assertThat(rowPieces.pieces()).hasSize(8);
    }
    
    @ParameterizedTest(name = "rowNum : {0}, expectedCompareNum : {1}")
    @CsvSource(value = {"6,1", "7,0", "8,-1"})
    void 행의_번호를_빼서_반환한다(int rowNum, int expectedCompareNum) {
        RowPieces rowPieces = new RowPieces(7);
        assertThat(rowPieces.compareTo(new RowPieces(rowNum))).isEqualTo(expectedCompareNum);
    }
    
    @ParameterizedTest
    @CsvSource(value = {"a,a,true","c,c,false","d,d,true"})
    void 출발_객체가_도착지_좌표로_이동할_수_있는지_판단(char sourceColumn,char destinationColumn, boolean expectedResult) {
        RowPieces targetRowPieces = new RowPieces(1);
        boolean isMovable = rowPieces.isMovable(targetRowPieces,sourceColumn,destinationColumn);
        Assertions.assertThat(isMovable).isEqualTo(expectedResult);
    }
}
