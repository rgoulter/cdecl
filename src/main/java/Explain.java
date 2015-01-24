import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

// Pass through a "gibberish" (C) declaration, come up with English terms.
class GibberishPhase extends CBaseVisitor<String> {
	TokenStreamRewriter rewriter;

	public GibberishPhase(BufferedTokenStream tokens) {
		rewriter = new TokenStreamRewriter(tokens);
	}

	@Override
	public String visitDeclaration(CParser.DeclarationContext ctx) {
		String declSpecs = visit(ctx.declarationSpecifiers());
		String theRest = visit(ctx.initDeclaratorList()); // assume only one variable for now.

		return theRest + declSpecs;
	}

	@Override
	public String visitDeclarationSpecifiers(CParser.DeclarationSpecifiersContext ctx) {
		return rewriter.getText(ctx.getSourceInterval());
	}

	@Override
	public String visitPointer(CParser.PointerContext ctx) {
		return "pointer to " + ((ctx.pointer() != null) ? visit(ctx.pointer()) : "");
	}

	// because "int x" fucks up, and so "int x = 3" fucks up also, need this:
	@Override
	public String visitInitDeclarator(CParser.InitDeclaratorContext ctx) {
		return visit(ctx.declarator());
	}

	@Override
	public String visitDeclaredParentheses(CParser.DeclaredParenthesesContext ctx) {
		return visit(ctx.declarator());
	}

	@Override
	public String visitDeclarator(CParser.DeclaratorContext ctx) {
		String directDecl = visit(ctx.directDeclarator());
		return directDecl + ((ctx.pointer() != null) ? visit(ctx.pointer()) : "");
	}

	@Override
	public String visitDeclaredIdentifier(CParser.DeclaredIdentifierContext ctx) {
		return rewriter.getText(ctx.getSourceInterval()) + " is ";
	}

	@Override
	public String visitDeclaredArray(CParser.DeclaredArrayContext ctx) {
		// assignmentExpression not guaranteed; may be '*' in func. arg.
		String directDecl = visit(ctx.directDeclarator());
		String arrSizeExpr = rewriter.getText(ctx.assignmentExpression().getSourceInterval()); // visit?
		return directDecl + "array " + arrSizeExpr + " of ";
	}

	@Override
	public String visitDeclaredFunctionPrototype(CParser.DeclaredFunctionPrototypeContext ctx) {
		String directDecl = visit(ctx.directDeclarator());
		// TODO: Look at each ... declSpecifiers2
		String params = rewriter.getText(ctx.parameterTypeList().getSourceInterval());

		return directDecl + "function (" + params + ") returning ";
	}
}

public class Explain {
	static String gibberishToEnglish(String gibberish) {
		CLexer lexer = new CLexer(new ANTLRInputStream(gibberish));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CParser parser = new CParser(tokens);

		ParseTree tree = parser.declaration(); // entry rule for parser

		ParseTreeWalker walker = new ParseTreeWalker();
		GibberishPhase tooler = new GibberishPhase(tokens);
		return tooler.visit(tree);
	}
}
