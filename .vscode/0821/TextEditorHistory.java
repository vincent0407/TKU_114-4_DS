import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {
    private Deque<String> undoStack = new ArrayDeque<>();
    private Deque<String> redoStack = new ArrayDeque<>();

    // 新增操作：將新文字推入 undo，並清空 redo
    public void execute(String action) {
        undoStack.push(action);
        redoStack.clear();
        System.out.println("[Execute] " + action);
        printState();
    }

    // Undo：將資料從 undo 移到 redo
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("[Undo] 失敗：Undo Stack 為空！");
            printState();
            return;
        }
        String action = undoStack.pop();
        redoStack.push(action);
        System.out.println("[Undo] 復原: " + action);
        printState();
    }

    // Redo：將資料從 redo 移回 undo
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("[Redo] 失敗：Redo Stack 為空！");
            printState();
            return;
        }
        String action = redoStack.pop();
        undoStack.push(action);
        System.out.println("[Redo] 重做: " + action);
        printState();
    }

    private void printState() {
        System.out.println("  -> Undo Stack: " + undoStack);
        System.out.println("  -> Redo Stack: " + redoStack + "\n");
    }

    public static void main(String[] args) {
        TextEditorHistory editor = new TextEditorHistory();

        // 空 stack 測試
        editor.undo();
        editor.redo();

        // 正常操作與 Undo/Redo 流程
        editor.execute("輸入 'Hello'");
        editor.execute("輸入 ' World'");
        editor.undo();
        editor.redo();
        
        // 新增操作會清空 redo
        editor.undo();
        editor.execute("輸入 ' Java'");
        editor.redo(); // 此時 redo 應為空
    }
}
