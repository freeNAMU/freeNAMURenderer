package com.github.freenamu.renderer;

import com.github.freenamu.node.*;
import com.github.freenamu.renderer.generator.ParagraphAnchorGenerator;

import java.util.List;

public class FreeNAMURenderer {
    private final ParagraphAnchorGenerator paragraphAnchorGenerator = new ParagraphAnchorGenerator();

    public String render(List<Node> article) {
        StringBuilder result = new StringBuilder();
        for (Node node : article) {
            result.append(render(node));
        }
        return result.toString();
    }

    private String render(Node node) {
        StringBuilder result = new StringBuilder();

        String className = node.getClass().getSimpleName();
        if (className.equals("Anchor")) renderAnchor(result, (Anchor) node);
        else if (className.equals("Blockquote")) renderBlockquote(result, (Blockquote) node);
        else if (className.equals("Bold")) renderBold(result, (Bold) node);
        else if (className.equals("Break")) result.append("<br>");
        else if (className.equals("Footnote")) {
        } else if (className.equals("HorizontalRule")) result.append("<hr>");
        else if (className.equals("Indent")) renderIndent(result, (Indent) node);
        else if (className.equals("Italic")) renderItalic(result, (Italic) node);
        else if (className.equals("Paragraph")) renderParagraph(result, (Paragraph) node);
        else if (className.equals("Strikeout")) renderStrikeout(result, (Strikeout) node);
        else if (className.equals("Subscript")) renderSubscript(result, (Subscript) node);
        else if (className.equals("Superscript")) renderSuperscript(result, (Superscript) node);
        else if (className.equals("Text")) result.append(((Text) node).getText());
        else if (className.equals("Underline")) renderUnderline(result, (Underline) node);

        return result.toString();
    }

    private void renderAnchor(StringBuilder result, Anchor anchor) {
        result.append("<a href=\"/document/view/");
        result.append(anchor.getReference());
        result.append("\">");
        result.append(render(anchor.getChildren()));
        result.append("</a>");
    }

    private void renderBlockquote(StringBuilder result, Blockquote blockquote) {
        result.append("<blockquote>");
        result.append(render(blockquote.getChildren()));
        result.append("</blockquote>");
    }

    private void renderBold(StringBuilder result, Bold bold) {
        result.append("<b>");
        result.append(render(bold.getChildren()));
        result.append("</b>");
    }

    private void renderIndent(StringBuilder result, Indent indent) {
        result.append("<div class=\"indent\">");
        result.append(render(indent.getChildren()));
        result.append("</div>");
    }

    private void renderItalic(StringBuilder result, Italic italic) {
        result.append("<i>");
        result.append(render(italic.getChildren()));
        result.append("</i>");
    }

    private void renderParagraph(StringBuilder result, Paragraph paragraph) {
        result.append(String.format("<h%1$d>", paragraph.getLevel()));
        result.append(paragraphAnchorGenerator.getNextAnchor(paragraph.getLevel()));
        result.append(" ");
        result.append(paragraph.getTitle());
        result.append(String.format("</h%1$d>", paragraph.getLevel()));
        result.append("<p>");
        result.append(render(paragraph.getChildren()));
        result.append("</p>");
    }

    private void renderStrikeout(StringBuilder result, Strikeout strikeout) {
        result.append("<s>");
        result.append(render(strikeout.getChildren()));
        result.append("</s>");
    }

    private void renderSubscript(StringBuilder result, Subscript subscript) {
        result.append("<sub>");
        result.append(render(subscript.getChildren()));
        result.append("</sub>");
    }

    private void renderSuperscript(StringBuilder result, Superscript superscript) {
        result.append("<sup>");
        result.append(render(superscript.getChildren()));
        result.append("</sup>");
    }

    private void renderUnderline(StringBuilder result, Underline underline) {
        result.append("<u>");
        result.append(render(underline.getChildren()));
        result.append("</u>");
    }
}
