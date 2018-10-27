// Generated from org/solidityj/Solidity.g4 by ANTLR 4.6
package org.solidityj;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.*;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SolidityParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, T__74=75, T__75=76, T__76=77, T__77=78, T__78=79, T__79=80, 
		T__80=81, T__81=82, T__82=83, T__83=84, T__84=85, T__85=86, T__86=87, 
		T__87=88, T__88=89, T__89=90, T__90=91, T__91=92, T__92=93, Int=94, Uint=95, 
		Byte=96, Fixed=97, Ufixed=98, VersionLiteral=99, BooleanLiteral=100, DecimalNumber=101, 
		HexNumber=102, NumberUnit=103, HexLiteral=104, ReservedKeyword=105, Identifier=106, 
		StringLiteral=107, WS=108, COMMENT=109, LINE_COMMENT=110;
	public static final int
		RULE_sourceUnit = 0, RULE_pragmaDirective = 1, RULE_pragmaName = 2, RULE_pragmaValue = 3, 
		RULE_version = 4, RULE_versionOperator = 5, RULE_versionConstraint = 6, 
		RULE_importDeclaration = 7, RULE_importDirective = 8, RULE_contractDefinition = 9, 
		RULE_inheritanceSpecifier = 10, RULE_contractPart = 11, RULE_stateVariableDeclaration = 12, 
		RULE_usingForDeclaration = 13, RULE_structDefinition = 14, RULE_modifierDefinition = 15, 
		RULE_functionDefinition = 16, RULE_eventDefinition = 17, RULE_enumValue = 18, 
		RULE_enumDefinition = 19, RULE_indexedParameterList = 20, RULE_parameterList = 21, 
		RULE_typeNameList = 22, RULE_variableDeclaration = 23, RULE_typeName = 24, 
		RULE_userDefinedTypeName = 25, RULE_mapping = 26, RULE_functionTypeName = 27, 
		RULE_storageLocation = 28, RULE_block = 29, RULE_statement = 30, RULE_expressionStatement = 31, 
		RULE_ifStatement = 32, RULE_whileStatement = 33, RULE_placeholderStatement = 34, 
		RULE_simpleStatement = 35, RULE_forStatement = 36, RULE_inlineAssemblyStatement = 37, 
		RULE_doWhileStatement = 38, RULE_continueStatement = 39, RULE_breakStatement = 40, 
		RULE_returnStatement = 41, RULE_throwStatement = 42, RULE_variableDeclarationStatement = 43, 
		RULE_identifierList = 44, RULE_elementaryTypeName = 45, RULE_expression = 46, 
		RULE_primaryExpression = 47, RULE_expressionList = 48, RULE_nameValueList = 49, 
		RULE_nameValue = 50, RULE_functionCall = 51, RULE_functionCallArguments = 52, 
		RULE_newExpression = 53, RULE_inlineAssemblyBlock = 54, RULE_assemblyItem = 55, 
		RULE_assemblyLocalBinding = 56, RULE_assemblyAssignment = 57, RULE_functionalAssemblyExpression = 58, 
		RULE_arrayLiteral = 59, RULE_elementaryTypeNameExpression = 60, RULE_numberLiteral = 61;
	public static final String[] ruleNames = {
		"sourceUnit", "pragmaDirective", "pragmaName", "pragmaValue", "version", 
		"versionOperator", "versionConstraint", "importDeclaration", "importDirective", 
		"contractDefinition", "inheritanceSpecifier", "contractPart", "stateVariableDeclaration", 
		"usingForDeclaration", "structDefinition", "modifierDefinition", "functionDefinition", 
		"eventDefinition", "enumValue", "enumDefinition", "indexedParameterList", 
		"parameterList", "typeNameList", "variableDeclaration", "typeName", "userDefinedTypeName", 
		"mapping", "functionTypeName", "storageLocation", "block", "statement", 
		"expressionStatement", "ifStatement", "whileStatement", "placeholderStatement", 
		"simpleStatement", "forStatement", "inlineAssemblyStatement", "doWhileStatement", 
		"continueStatement", "breakStatement", "returnStatement", "throwStatement", 
		"variableDeclarationStatement", "identifierList", "elementaryTypeName", 
		"expression", "primaryExpression", "expressionList", "nameValueList", 
		"nameValue", "functionCall", "functionCallArguments", "newExpression", 
		"inlineAssemblyBlock", "assemblyItem", "assemblyLocalBinding", "assemblyAssignment", 
		"functionalAssemblyExpression", "arrayLiteral", "elementaryTypeNameExpression", 
		"numberLiteral"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'pragma'", "';'", "'^'", "'>='", "'>'", "'<'", "'<='", "'as'", 
		"'import'", "'*'", "'from'", "'{'", "','", "'}'", "'contract'", "'interface'", 
		"'library'", "'is'", "'('", "')'", "'public'", "'internal'", "'private'", 
		"'constant'", "'='", "'using'", "'for'", "'struct'", "'modifier'", "'function'", 
		"'payable'", "'external'", "'returns'", "'event'", "'anonymous'", "'enum'", 
		"'indexed'", "'['", "']'", "'.'", "'mapping'", "'=>'", "'memory'", "'storage'", 
		"'if'", "'else'", "'while'", "'_'", "'assembly'", "'do'", "'continue'", 
		"'break'", "'return'", "'throw'", "'var'", "'address'", "'bool'", "'string'", 
		"'++'", "'--'", "'new'", "'+'", "'-'", "'after'", "'delete'", "'!'", "'~'", 
		"'**'", "'/'", "'%'", "'<<'", "'>>'", "'&'", "'|'", "'=='", "'!='", "'&&'", 
		"'||'", "'?'", "':'", "'|='", "'^='", "'&='", "'<<='", "'>>='", "'+='", 
		"'-='", "'*='", "'/='", "'%='", "'let'", "':='", "'=:'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "Int", "Uint", 
		"Byte", "Fixed", "Ufixed", "VersionLiteral", "BooleanLiteral", "DecimalNumber", 
		"HexNumber", "NumberUnit", "HexLiteral", "ReservedKeyword", "Identifier", 
		"StringLiteral", "WS", "COMMENT", "LINE_COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Solidity.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SolidityParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SourceUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SolidityParser.EOF, 0); }
		public List<PragmaDirectiveContext> pragmaDirective() {
			return getRuleContexts(PragmaDirectiveContext.class);
		}
		public PragmaDirectiveContext pragmaDirective(int i) {
			return getRuleContext(PragmaDirectiveContext.class,i);
		}
		public List<ImportDirectiveContext> importDirective() {
			return getRuleContexts(ImportDirectiveContext.class);
		}
		public ImportDirectiveContext importDirective(int i) {
			return getRuleContext(ImportDirectiveContext.class,i);
		}
		public List<ContractDefinitionContext> contractDefinition() {
			return getRuleContexts(ContractDefinitionContext.class);
		}
		public ContractDefinitionContext contractDefinition(int i) {
			return getRuleContext(ContractDefinitionContext.class,i);
		}
		public SourceUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterSourceUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitSourceUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitSourceUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceUnitContext sourceUnit() throws RecognitionException {
		SourceUnitContext _localctx = new SourceUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sourceUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__8) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) {
				{
				setState(127);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(124);
					pragmaDirective();
					}
					break;
				case T__8:
					{
					setState(125);
					importDirective();
					}
					break;
				case T__14:
				case T__15:
				case T__16:
					{
					setState(126);
					contractDefinition();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(132);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PragmaDirectiveContext extends ParserRuleContext {
		public PragmaNameContext pragmaName() {
			return getRuleContext(PragmaNameContext.class,0);
		}
		public PragmaValueContext pragmaValue() {
			return getRuleContext(PragmaValueContext.class,0);
		}
		public PragmaDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pragmaDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterPragmaDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitPragmaDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitPragmaDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PragmaDirectiveContext pragmaDirective() throws RecognitionException {
		PragmaDirectiveContext _localctx = new PragmaDirectiveContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_pragmaDirective);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(T__0);
			setState(135);
			pragmaName();
			setState(136);
			pragmaValue();
			setState(137);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PragmaNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public PragmaNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pragmaName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterPragmaName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitPragmaName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitPragmaName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PragmaNameContext pragmaName() throws RecognitionException {
		PragmaNameContext _localctx = new PragmaNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pragmaName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PragmaValueContext extends ParserRuleContext {
		public VersionContext version() {
			return getRuleContext(VersionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PragmaValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pragmaValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterPragmaValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitPragmaValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitPragmaValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PragmaValueContext pragmaValue() throws RecognitionException {
		PragmaValueContext _localctx = new PragmaValueContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_pragmaValue);
		try {
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case VersionLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				version();
				}
				break;
			case T__18:
			case T__29:
			case T__37:
			case T__40:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__61:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case Int:
			case Uint:
			case Byte:
			case Fixed:
			case Ufixed:
			case BooleanLiteral:
			case DecimalNumber:
			case HexNumber:
			case HexLiteral:
			case Identifier:
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VersionContext extends ParserRuleContext {
		public List<VersionConstraintContext> versionConstraint() {
			return getRuleContexts(VersionConstraintContext.class);
		}
		public VersionConstraintContext versionConstraint(int i) {
			return getRuleContext(VersionConstraintContext.class,i);
		}
		public VersionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_version; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterVersion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitVersion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitVersion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VersionContext version() throws RecognitionException {
		VersionContext _localctx = new VersionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_version);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			versionConstraint();
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6))) != 0) || _la==VersionLiteral) {
				{
				setState(146);
				versionConstraint();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VersionOperatorContext extends ParserRuleContext {
		public VersionOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_versionOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterVersionOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitVersionOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitVersionOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VersionOperatorContext versionOperator() throws RecognitionException {
		VersionOperatorContext _localctx = new VersionOperatorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_versionOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VersionConstraintContext extends ParserRuleContext {
		public TerminalNode VersionLiteral() { return getToken(SolidityParser.VersionLiteral, 0); }
		public VersionOperatorContext versionOperator() {
			return getRuleContext(VersionOperatorContext.class,0);
		}
		public VersionConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_versionConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterVersionConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitVersionConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitVersionConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VersionConstraintContext versionConstraint() throws RecognitionException {
		VersionConstraintContext _localctx = new VersionConstraintContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_versionConstraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6))) != 0)) {
				{
				setState(151);
				versionOperator();
				}
			}

			setState(154);
			match(VersionLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportDeclarationContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(SolidityParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SolidityParser.Identifier, i);
		}
		public ImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterImportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitImportDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitImportDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclarationContext importDeclaration() throws RecognitionException {
		ImportDeclarationContext _localctx = new ImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_importDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(Identifier);
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(157);
				match(T__7);
				setState(158);
				match(Identifier);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportDirectiveContext extends ParserRuleContext {
		public TerminalNode StringLiteral() { return getToken(SolidityParser.StringLiteral, 0); }
		public List<TerminalNode> Identifier() { return getTokens(SolidityParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SolidityParser.Identifier, i);
		}
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public ImportDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterImportDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitImportDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitImportDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDirectiveContext importDirective() throws RecognitionException {
		ImportDirectiveContext _localctx = new ImportDirectiveContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_importDirective);
		int _la;
		try {
			setState(192);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(161);
				match(T__8);
				setState(162);
				match(StringLiteral);
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(163);
					match(T__7);
					setState(164);
					match(Identifier);
					}
				}

				setState(167);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				match(T__8);
				setState(169);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==Identifier) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(170);
					match(T__7);
					setState(171);
					match(Identifier);
					}
				}

				setState(174);
				match(T__10);
				setState(175);
				match(StringLiteral);
				setState(176);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(177);
				match(T__8);
				setState(178);
				match(T__11);
				setState(179);
				importDeclaration();
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(180);
					match(T__12);
					setState(181);
					importDeclaration();
					}
					}
					setState(186);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(187);
				match(T__13);
				setState(188);
				match(T__10);
				setState(189);
				match(StringLiteral);
				setState(190);
				match(T__1);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContractDefinitionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public List<InheritanceSpecifierContext> inheritanceSpecifier() {
			return getRuleContexts(InheritanceSpecifierContext.class);
		}
		public InheritanceSpecifierContext inheritanceSpecifier(int i) {
			return getRuleContext(InheritanceSpecifierContext.class,i);
		}
		public List<ContractPartContext> contractPart() {
			return getRuleContexts(ContractPartContext.class);
		}
		public ContractPartContext contractPart(int i) {
			return getRuleContext(ContractPartContext.class,i);
		}
		public ContractDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contractDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterContractDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitContractDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitContractDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContractDefinitionContext contractDefinition() throws RecognitionException {
		ContractDefinitionContext _localctx = new ContractDefinitionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_contractDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(195);
			match(Identifier);
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__17) {
				{
				setState(196);
				match(T__17);
				setState(197);
				inheritanceSpecifier();
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(198);
					match(T__12);
					setState(199);
					inheritanceSpecifier();
					}
					}
					setState(204);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(207);
			match(T__11);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__25) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__33) | (1L << T__35) | (1L << T__40) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57))) != 0) || ((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & ((1L << (Int - 94)) | (1L << (Uint - 94)) | (1L << (Byte - 94)) | (1L << (Fixed - 94)) | (1L << (Ufixed - 94)) | (1L << (Identifier - 94)))) != 0)) {
				{
				{
				setState(208);
				contractPart();
				}
				}
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(214);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InheritanceSpecifierContext extends ParserRuleContext {
		public UserDefinedTypeNameContext userDefinedTypeName() {
			return getRuleContext(UserDefinedTypeNameContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public InheritanceSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inheritanceSpecifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterInheritanceSpecifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitInheritanceSpecifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitInheritanceSpecifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InheritanceSpecifierContext inheritanceSpecifier() throws RecognitionException {
		InheritanceSpecifierContext _localctx = new InheritanceSpecifierContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_inheritanceSpecifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			userDefinedTypeName();
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(217);
				match(T__18);
				setState(218);
				expression(0);
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(219);
					match(T__12);
					setState(220);
					expression(0);
					}
					}
					setState(225);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(226);
				match(T__19);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContractPartContext extends ParserRuleContext {
		public StateVariableDeclarationContext stateVariableDeclaration() {
			return getRuleContext(StateVariableDeclarationContext.class,0);
		}
		public UsingForDeclarationContext usingForDeclaration() {
			return getRuleContext(UsingForDeclarationContext.class,0);
		}
		public StructDefinitionContext structDefinition() {
			return getRuleContext(StructDefinitionContext.class,0);
		}
		public ModifierDefinitionContext modifierDefinition() {
			return getRuleContext(ModifierDefinitionContext.class,0);
		}
		public FunctionDefinitionContext functionDefinition() {
			return getRuleContext(FunctionDefinitionContext.class,0);
		}
		public EventDefinitionContext eventDefinition() {
			return getRuleContext(EventDefinitionContext.class,0);
		}
		public EnumDefinitionContext enumDefinition() {
			return getRuleContext(EnumDefinitionContext.class,0);
		}
		public ContractPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contractPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterContractPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitContractPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitContractPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContractPartContext contractPart() throws RecognitionException {
		ContractPartContext _localctx = new ContractPartContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_contractPart);
		try {
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				stateVariableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				usingForDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(232);
				structDefinition();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(233);
				modifierDefinition();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(234);
				functionDefinition();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(235);
				eventDefinition();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(236);
				enumDefinition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StateVariableDeclarationContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StateVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stateVariableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterStateVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitStateVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitStateVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StateVariableDeclarationContext stateVariableDeclaration() throws RecognitionException {
		StateVariableDeclarationContext _localctx = new StateVariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stateVariableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			typeName(0);
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23))) != 0)) {
				{
				{
				setState(240);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(246);
			match(Identifier);
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__24) {
				{
				setState(247);
				match(T__24);
				setState(248);
				expression(0);
				}
			}

			setState(251);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UsingForDeclarationContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public UsingForDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_usingForDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterUsingForDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitUsingForDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitUsingForDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UsingForDeclarationContext usingForDeclaration() throws RecognitionException {
		UsingForDeclarationContext _localctx = new UsingForDeclarationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_usingForDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			match(T__25);
			setState(254);
			match(Identifier);
			setState(255);
			match(T__26);
			setState(258);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				{
				setState(256);
				match(T__9);
				}
				break;
			case T__29:
			case T__40:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case Int:
			case Uint:
			case Byte:
			case Fixed:
			case Ufixed:
			case Identifier:
				{
				setState(257);
				typeName(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(260);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructDefinitionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public StructDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterStructDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitStructDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitStructDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructDefinitionContext structDefinition() throws RecognitionException {
		StructDefinitionContext _localctx = new StructDefinitionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_structDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(T__27);
			setState(263);
			match(Identifier);
			setState(264);
			match(T__11);
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__40) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57))) != 0) || ((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & ((1L << (Int - 94)) | (1L << (Uint - 94)) | (1L << (Byte - 94)) | (1L << (Fixed - 94)) | (1L << (Ufixed - 94)) | (1L << (Identifier - 94)))) != 0)) {
				{
				setState(265);
				variableDeclaration();
				setState(266);
				match(T__1);
				setState(272);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__40) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57))) != 0) || ((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & ((1L << (Int - 94)) | (1L << (Uint - 94)) | (1L << (Byte - 94)) | (1L << (Fixed - 94)) | (1L << (Ufixed - 94)) | (1L << (Identifier - 94)))) != 0)) {
					{
					{
					setState(267);
					variableDeclaration();
					setState(268);
					match(T__1);
					}
					}
					setState(274);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(277);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModifierDefinitionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public ModifierDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifierDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterModifierDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitModifierDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitModifierDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModifierDefinitionContext modifierDefinition() throws RecognitionException {
		ModifierDefinitionContext _localctx = new ModifierDefinitionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_modifierDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(T__28);
			setState(280);
			match(Identifier);
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(281);
				parameterList();
				}
			}

			setState(284);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefinitionContext extends ParserRuleContext {
		public List<ParameterListContext> parameterList() {
			return getRuleContexts(ParameterListContext.class);
		}
		public ParameterListContext parameterList(int i) {
			return getRuleContext(ParameterListContext.class,i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<TerminalNode> Identifier() { return getTokens(SolidityParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SolidityParser.Identifier, i);
		}
		public List<FunctionCallContext> functionCall() {
			return getRuleContexts(FunctionCallContext.class);
		}
		public FunctionCallContext functionCall(int i) {
			return getRuleContext(FunctionCallContext.class,i);
		}
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterFunctionDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitFunctionDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitFunctionDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(T__29);
			setState(288);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(287);
				match(Identifier);
				}
			}

			setState(290);
			parameterList();
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__40) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__60))) != 0) || ((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & ((1L << (Int - 94)) | (1L << (Uint - 94)) | (1L << (Byte - 94)) | (1L << (Fixed - 94)) | (1L << (Ufixed - 94)) | (1L << (Identifier - 94)))) != 0)) {
				{
				setState(299);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(291);
					functionCall();
					}
					break;
				case 2:
					{
					setState(292);
					match(Identifier);
					}
					break;
				case 3:
					{
					setState(293);
					match(T__23);
					}
					break;
				case 4:
					{
					setState(294);
					match(T__30);
					}
					break;
				case 5:
					{
					setState(295);
					match(T__31);
					}
					break;
				case 6:
					{
					setState(296);
					match(T__20);
					}
					break;
				case 7:
					{
					setState(297);
					match(T__21);
					}
					break;
				case 8:
					{
					setState(298);
					match(T__22);
					}
					break;
				}
				}
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__32) {
				{
				setState(304);
				match(T__32);
				setState(305);
				parameterList();
				}
			}

			setState(310);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				{
				setState(308);
				match(T__1);
				}
				break;
			case T__11:
				{
				setState(309);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EventDefinitionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public IndexedParameterListContext indexedParameterList() {
			return getRuleContext(IndexedParameterListContext.class,0);
		}
		public EventDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eventDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterEventDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitEventDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitEventDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EventDefinitionContext eventDefinition() throws RecognitionException {
		EventDefinitionContext _localctx = new EventDefinitionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_eventDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(T__33);
			setState(313);
			match(Identifier);
			setState(314);
			indexedParameterList();
			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__34) {
				{
				setState(315);
				match(T__34);
				}
			}

			setState(318);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumValueContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public EnumValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterEnumValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitEnumValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitEnumValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumValueContext enumValue() throws RecognitionException {
		EnumValueContext _localctx = new EnumValueContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_enumValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumDefinitionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public List<EnumValueContext> enumValue() {
			return getRuleContexts(EnumValueContext.class);
		}
		public EnumValueContext enumValue(int i) {
			return getRuleContext(EnumValueContext.class,i);
		}
		public EnumDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterEnumDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitEnumDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitEnumDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumDefinitionContext enumDefinition() throws RecognitionException {
		EnumDefinitionContext _localctx = new EnumDefinitionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_enumDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			match(T__35);
			setState(323);
			match(Identifier);
			setState(324);
			match(T__11);
			setState(326);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(325);
				enumValue();
				}
			}

			setState(332);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(328);
				match(T__12);
				setState(329);
				enumValue();
				}
				}
				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(335);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexedParameterListContext extends ParserRuleContext {
		public List<TypeNameContext> typeName() {
			return getRuleContexts(TypeNameContext.class);
		}
		public TypeNameContext typeName(int i) {
			return getRuleContext(TypeNameContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(SolidityParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SolidityParser.Identifier, i);
		}
		public IndexedParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexedParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterIndexedParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitIndexedParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitIndexedParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexedParameterListContext indexedParameterList() throws RecognitionException {
		IndexedParameterListContext _localctx = new IndexedParameterListContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_indexedParameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			match(T__18);
			setState(358);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__40) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57))) != 0) || ((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & ((1L << (Int - 94)) | (1L << (Uint - 94)) | (1L << (Byte - 94)) | (1L << (Fixed - 94)) | (1L << (Ufixed - 94)) | (1L << (Identifier - 94)))) != 0)) {
				{
				setState(338);
				typeName(0);
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__36) {
					{
					setState(339);
					match(T__36);
					}
				}

				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(342);
					match(Identifier);
					}
				}

				setState(355);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(345);
					match(T__12);
					setState(346);
					typeName(0);
					setState(348);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__36) {
						{
						setState(347);
						match(T__36);
						}
					}

					setState(351);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==Identifier) {
						{
						setState(350);
						match(Identifier);
						}
					}

					}
					}
					setState(357);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(360);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public List<TypeNameContext> typeName() {
			return getRuleContexts(TypeNameContext.class);
		}
		public TypeNameContext typeName(int i) {
			return getRuleContext(TypeNameContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(SolidityParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SolidityParser.Identifier, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			match(T__18);
			setState(377);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__40) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57))) != 0) || ((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & ((1L << (Int - 94)) | (1L << (Uint - 94)) | (1L << (Byte - 94)) | (1L << (Fixed - 94)) | (1L << (Ufixed - 94)) | (1L << (Identifier - 94)))) != 0)) {
				{
				setState(363);
				typeName(0);
				setState(365);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(364);
					match(Identifier);
					}
				}

				setState(374);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(367);
					match(T__12);
					setState(368);
					typeName(0);
					setState(370);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==Identifier) {
						{
						setState(369);
						match(Identifier);
						}
					}

					}
					}
					setState(376);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(379);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeNameListContext extends ParserRuleContext {
		public List<TypeNameContext> typeName() {
			return getRuleContexts(TypeNameContext.class);
		}
		public TypeNameContext typeName(int i) {
			return getRuleContext(TypeNameContext.class,i);
		}
		public TypeNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterTypeNameList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitTypeNameList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitTypeNameList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameListContext typeNameList() throws RecognitionException {
		TypeNameListContext _localctx = new TypeNameListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_typeNameList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			match(T__18);
			setState(390);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__40) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57))) != 0) || ((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & ((1L << (Int - 94)) | (1L << (Uint - 94)) | (1L << (Byte - 94)) | (1L << (Fixed - 94)) | (1L << (Ufixed - 94)) | (1L << (Identifier - 94)))) != 0)) {
				{
				setState(382);
				typeName(0);
				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(383);
					match(T__12);
					setState(384);
					typeName(0);
					}
					}
					setState(389);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(392);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public StorageLocationContext storageLocation() {
			return getRuleContext(StorageLocationContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(394);
			typeName(0);
			setState(396);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__42 || _la==T__43) {
				{
				setState(395);
				storageLocation();
				}
			}

			setState(398);
			match(Identifier);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeNameContext extends ParserRuleContext {
		public ElementaryTypeNameContext elementaryTypeName() {
			return getRuleContext(ElementaryTypeNameContext.class,0);
		}
		public UserDefinedTypeNameContext userDefinedTypeName() {
			return getRuleContext(UserDefinedTypeNameContext.class,0);
		}
		public MappingContext mapping() {
			return getRuleContext(MappingContext.class,0);
		}
		public FunctionTypeNameContext functionTypeName() {
			return getRuleContext(FunctionTypeNameContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		return typeName(0);
	}

	private TypeNameContext typeName(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeNameContext _localctx = new TypeNameContext(_ctx, _parentState);
		TypeNameContext _prevctx = _localctx;
		int _startState = 48;
		enterRecursionRule(_localctx, 48, RULE_typeName, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case Int:
			case Uint:
			case Byte:
			case Fixed:
			case Ufixed:
				{
				setState(401);
				elementaryTypeName();
				}
				break;
			case Identifier:
				{
				setState(402);
				userDefinedTypeName();
				}
				break;
			case T__40:
				{
				setState(403);
				mapping();
				}
				break;
			case T__29:
				{
				setState(404);
				functionTypeName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(415);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeNameContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_typeName);
					setState(407);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(408);
					match(T__37);
					setState(410);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__29) | (1L << T__37) | (1L << T__40) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (Int - 64)) | (1L << (Uint - 64)) | (1L << (Byte - 64)) | (1L << (Fixed - 64)) | (1L << (Ufixed - 64)) | (1L << (BooleanLiteral - 64)) | (1L << (DecimalNumber - 64)) | (1L << (HexNumber - 64)) | (1L << (HexLiteral - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
						{
						setState(409);
						expression(0);
						}
					}

					setState(412);
					match(T__38);
					}
					} 
				}
				setState(417);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UserDefinedTypeNameContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(SolidityParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SolidityParser.Identifier, i);
		}
		public UserDefinedTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_userDefinedTypeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterUserDefinedTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitUserDefinedTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitUserDefinedTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UserDefinedTypeNameContext userDefinedTypeName() throws RecognitionException {
		UserDefinedTypeNameContext _localctx = new UserDefinedTypeNameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_userDefinedTypeName);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			match(Identifier);
			setState(423);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(419);
					match(T__39);
					setState(420);
					match(Identifier);
					}
					} 
				}
				setState(425);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MappingContext extends ParserRuleContext {
		public ElementaryTypeNameContext elementaryTypeName() {
			return getRuleContext(ElementaryTypeNameContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public MappingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapping; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterMapping(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitMapping(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitMapping(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MappingContext mapping() throws RecognitionException {
		MappingContext _localctx = new MappingContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_mapping);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			match(T__40);
			setState(427);
			match(T__18);
			setState(428);
			elementaryTypeName();
			setState(429);
			match(T__41);
			setState(430);
			typeName(0);
			setState(431);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionTypeNameContext extends ParserRuleContext {
		public List<TypeNameListContext> typeNameList() {
			return getRuleContexts(TypeNameListContext.class);
		}
		public TypeNameListContext typeNameList(int i) {
			return getRuleContext(TypeNameListContext.class,i);
		}
		public FunctionTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionTypeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterFunctionTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitFunctionTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitFunctionTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionTypeNameContext functionTypeName() throws RecognitionException {
		FunctionTypeNameContext _localctx = new FunctionTypeNameContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_functionTypeName);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			match(T__29);
			setState(434);
			typeNameList();
			setState(438);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(435);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__23) | (1L << T__30) | (1L << T__31))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(440);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			}
			setState(443);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				{
				setState(441);
				match(T__32);
				setState(442);
				typeNameList();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StorageLocationContext extends ParserRuleContext {
		public StorageLocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_storageLocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterStorageLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitStorageLocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitStorageLocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StorageLocationContext storageLocation() throws RecognitionException {
		StorageLocationContext _localctx = new StorageLocationContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_storageLocation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			_la = _input.LA(1);
			if ( !(_la==T__42 || _la==T__43) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			match(T__11);
			setState(451);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__18) | (1L << T__26) | (1L << T__29) | (1L << T__37) | (1L << T__40) | (1L << T__44) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (Int - 64)) | (1L << (Uint - 64)) | (1L << (Byte - 64)) | (1L << (Fixed - 64)) | (1L << (Ufixed - 64)) | (1L << (BooleanLiteral - 64)) | (1L << (DecimalNumber - 64)) | (1L << (HexNumber - 64)) | (1L << (HexLiteral - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				{
				setState(448);
				statement();
				}
				}
				setState(453);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(454);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public InlineAssemblyStatementContext inlineAssemblyStatement() {
			return getRuleContext(InlineAssemblyStatementContext.class,0);
		}
		public DoWhileStatementContext doWhileStatement() {
			return getRuleContext(DoWhileStatementContext.class,0);
		}
		public PlaceholderStatementContext placeholderStatement() {
			return getRuleContext(PlaceholderStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public ThrowStatementContext throwStatement() {
			return getRuleContext(ThrowStatementContext.class,0);
		}
		public SimpleStatementContext simpleStatement() {
			return getRuleContext(SimpleStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_statement);
		try {
			setState(468);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__44:
				enterOuterAlt(_localctx, 1);
				{
				setState(456);
				ifStatement();
				}
				break;
			case T__46:
				enterOuterAlt(_localctx, 2);
				{
				setState(457);
				whileStatement();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 3);
				{
				setState(458);
				forStatement();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(459);
				block();
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 5);
				{
				setState(460);
				inlineAssemblyStatement();
				}
				break;
			case T__49:
				enterOuterAlt(_localctx, 6);
				{
				setState(461);
				doWhileStatement();
				}
				break;
			case T__47:
				enterOuterAlt(_localctx, 7);
				{
				setState(462);
				placeholderStatement();
				}
				break;
			case T__50:
				enterOuterAlt(_localctx, 8);
				{
				setState(463);
				continueStatement();
				}
				break;
			case T__51:
				enterOuterAlt(_localctx, 9);
				{
				setState(464);
				breakStatement();
				}
				break;
			case T__52:
				enterOuterAlt(_localctx, 10);
				{
				setState(465);
				returnStatement();
				}
				break;
			case T__53:
				enterOuterAlt(_localctx, 11);
				{
				setState(466);
				throwStatement();
				}
				break;
			case T__18:
			case T__29:
			case T__37:
			case T__40:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__61:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case Int:
			case Uint:
			case Byte:
			case Fixed:
			case Ufixed:
			case BooleanLiteral:
			case DecimalNumber:
			case HexNumber:
			case HexLiteral:
			case Identifier:
			case StringLiteral:
				enterOuterAlt(_localctx, 12);
				{
				setState(467);
				simpleStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitExpressionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(470);
			expression(0);
			setState(471);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(473);
			match(T__44);
			setState(474);
			match(T__18);
			setState(475);
			expression(0);
			setState(476);
			match(T__19);
			setState(477);
			statement();
			setState(480);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(478);
				match(T__45);
				setState(479);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482);
			match(T__46);
			setState(483);
			match(T__18);
			setState(484);
			expression(0);
			setState(485);
			match(T__19);
			setState(486);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlaceholderStatementContext extends ParserRuleContext {
		public PlaceholderStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_placeholderStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterPlaceholderStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitPlaceholderStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitPlaceholderStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlaceholderStatementContext placeholderStatement() throws RecognitionException {
		PlaceholderStatementContext _localctx = new PlaceholderStatementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_placeholderStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(488);
			match(T__47);
			setState(489);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleStatementContext extends ParserRuleContext {
		public VariableDeclarationStatementContext variableDeclarationStatement() {
			return getRuleContext(VariableDeclarationStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public SimpleStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterSimpleStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitSimpleStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitSimpleStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleStatementContext simpleStatement() throws RecognitionException {
		SimpleStatementContext _localctx = new SimpleStatementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_simpleStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(491);
				variableDeclarationStatement();
				}
				break;
			case 2:
				{
				setState(492);
				expressionStatement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public SimpleStatementContext simpleStatement() {
			return getRuleContext(SimpleStatementContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_forStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(495);
			match(T__26);
			setState(496);
			match(T__18);
			setState(500);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(497);
				simpleStatement();
				setState(498);
				match(T__1);
				}
				break;
			}
			setState(505);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(502);
				expression(0);
				setState(503);
				match(T__1);
				}
				break;
			}
			setState(508);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__29) | (1L << T__37) | (1L << T__40) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (Int - 64)) | (1L << (Uint - 64)) | (1L << (Byte - 64)) | (1L << (Fixed - 64)) | (1L << (Ufixed - 64)) | (1L << (BooleanLiteral - 64)) | (1L << (DecimalNumber - 64)) | (1L << (HexNumber - 64)) | (1L << (HexLiteral - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				setState(507);
				expression(0);
				}
			}

			setState(510);
			match(T__19);
			setState(511);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InlineAssemblyStatementContext extends ParserRuleContext {
		public InlineAssemblyBlockContext inlineAssemblyBlock() {
			return getRuleContext(InlineAssemblyBlockContext.class,0);
		}
		public InlineAssemblyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineAssemblyStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterInlineAssemblyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitInlineAssemblyStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitInlineAssemblyStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InlineAssemblyStatementContext inlineAssemblyStatement() throws RecognitionException {
		InlineAssemblyStatementContext _localctx = new InlineAssemblyStatementContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_inlineAssemblyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			match(T__48);
			setState(514);
			inlineAssemblyBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoWhileStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DoWhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doWhileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterDoWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitDoWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitDoWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoWhileStatementContext doWhileStatement() throws RecognitionException {
		DoWhileStatementContext _localctx = new DoWhileStatementContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_doWhileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(516);
			match(T__49);
			setState(517);
			statement();
			setState(518);
			match(T__46);
			setState(519);
			match(T__18);
			setState(520);
			expression(0);
			setState(521);
			match(T__19);
			setState(522);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStatementContext extends ParserRuleContext {
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(524);
			match(T__50);
			setState(525);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStatementContext extends ParserRuleContext {
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(527);
			match(T__51);
			setState(528);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(530);
			match(T__52);
			setState(532);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__29) | (1L << T__37) | (1L << T__40) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (Int - 64)) | (1L << (Uint - 64)) | (1L << (Byte - 64)) | (1L << (Fixed - 64)) | (1L << (Ufixed - 64)) | (1L << (BooleanLiteral - 64)) | (1L << (DecimalNumber - 64)) | (1L << (HexNumber - 64)) | (1L << (HexLiteral - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				setState(531);
				expression(0);
				}
			}

			setState(534);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThrowStatementContext extends ParserRuleContext {
		public ThrowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterThrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitThrowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitThrowStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThrowStatementContext throwStatement() throws RecognitionException {
		ThrowStatementContext _localctx = new ThrowStatementContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(536);
			match(T__53);
			setState(537);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationStatementContext extends ParserRuleContext {
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitVariableDeclarationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitVariableDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationStatementContext variableDeclarationStatement() throws RecognitionException {
		VariableDeclarationStatementContext _localctx = new VariableDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_variableDeclarationStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(542);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				{
				setState(539);
				match(T__54);
				setState(540);
				identifierList();
				}
				break;
			case 2:
				{
				setState(541);
				variableDeclaration();
				}
				break;
			}
			setState(546);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__24) {
				{
				setState(544);
				match(T__24);
				setState(545);
				expression(0);
				}
			}

			setState(548);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierListContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(SolidityParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SolidityParser.Identifier, i);
		}
		public IdentifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterIdentifierList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitIdentifierList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitIdentifierList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierListContext identifierList() throws RecognitionException {
		IdentifierListContext _localctx = new IdentifierListContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_identifierList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(550);
			match(T__18);
			setState(557);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(552);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==Identifier) {
						{
						setState(551);
						match(Identifier);
						}
					}

					setState(554);
					match(T__12);
					}
					} 
				}
				setState(559);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			}
			setState(561);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(560);
				match(Identifier);
				}
			}

			setState(563);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementaryTypeNameContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(SolidityParser.Int, 0); }
		public TerminalNode Uint() { return getToken(SolidityParser.Uint, 0); }
		public TerminalNode Byte() { return getToken(SolidityParser.Byte, 0); }
		public TerminalNode Fixed() { return getToken(SolidityParser.Fixed, 0); }
		public TerminalNode Ufixed() { return getToken(SolidityParser.Ufixed, 0); }
		public ElementaryTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementaryTypeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterElementaryTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitElementaryTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitElementaryTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementaryTypeNameContext elementaryTypeName() throws RecognitionException {
		ElementaryTypeNameContext _localctx = new ElementaryTypeNameContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_elementaryTypeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(565);
			_la = _input.LA(1);
			if ( !(((((_la - 55)) & ~0x3f) == 0 && ((1L << (_la - 55)) & ((1L << (T__54 - 55)) | (1L << (T__55 - 55)) | (1L << (T__56 - 55)) | (1L << (T__57 - 55)) | (1L << (Int - 55)) | (1L << (Uint - 55)) | (1L << (Byte - 55)) | (1L << (Fixed - 55)) | (1L << (Ufixed - 55)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 92;
		enterRecursionRule(_localctx, 92, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(586);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				{
				setState(568);
				functionCall();
				}
				break;
			case 2:
				{
				setState(569);
				match(T__60);
				setState(570);
				typeName(0);
				}
				break;
			case 3:
				{
				setState(571);
				match(T__18);
				setState(572);
				expression(0);
				setState(573);
				match(T__19);
				}
				break;
			case 4:
				{
				setState(575);
				_la = _input.LA(1);
				if ( !(_la==T__58 || _la==T__59) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(576);
				expression(19);
				}
				break;
			case 5:
				{
				setState(577);
				_la = _input.LA(1);
				if ( !(_la==T__61 || _la==T__62) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(578);
				expression(18);
				}
				break;
			case 6:
				{
				setState(579);
				_la = _input.LA(1);
				if ( !(_la==T__63 || _la==T__64) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(580);
				expression(17);
				}
				break;
			case 7:
				{
				setState(581);
				match(T__65);
				setState(582);
				expression(16);
				}
				break;
			case 8:
				{
				setState(583);
				match(T__66);
				setState(584);
				expression(15);
				}
				break;
			case 9:
				{
				setState(585);
				primaryExpression();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(642);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(640);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(588);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(589);
						match(T__67);
						setState(590);
						expression(15);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(591);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(592);
						_la = _input.LA(1);
						if ( !(((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (T__9 - 10)) | (1L << (T__68 - 10)) | (1L << (T__69 - 10)))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(593);
						expression(14);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(594);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(595);
						_la = _input.LA(1);
						if ( !(_la==T__61 || _la==T__62) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(596);
						expression(13);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(597);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(598);
						_la = _input.LA(1);
						if ( !(_la==T__70 || _la==T__71) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(599);
						expression(12);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(600);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(601);
						match(T__72);
						setState(602);
						expression(11);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(603);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(604);
						match(T__2);
						setState(605);
						expression(10);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(606);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(607);
						match(T__73);
						setState(608);
						expression(9);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(609);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(610);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(611);
						expression(8);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(612);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(613);
						_la = _input.LA(1);
						if ( !(_la==T__74 || _la==T__75) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(614);
						expression(7);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(615);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(616);
						match(T__76);
						setState(617);
						expression(6);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(618);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(619);
						match(T__77);
						setState(620);
						expression(5);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(621);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(622);
						match(T__78);
						setState(623);
						expression(0);
						setState(624);
						match(T__79);
						setState(625);
						expression(4);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(627);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(628);
						_la = _input.LA(1);
						if ( !(_la==T__24 || ((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & ((1L << (T__80 - 81)) | (1L << (T__81 - 81)) | (1L << (T__82 - 81)) | (1L << (T__83 - 81)) | (1L << (T__84 - 81)) | (1L << (T__85 - 81)) | (1L << (T__86 - 81)) | (1L << (T__87 - 81)) | (1L << (T__88 - 81)) | (1L << (T__89 - 81)))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(629);
						expression(3);
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(630);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(631);
						_la = _input.LA(1);
						if ( !(_la==T__58 || _la==T__59) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(632);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(633);
						match(T__37);
						setState(634);
						expression(0);
						setState(635);
						match(T__38);
						}
						break;
					case 16:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(637);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(638);
						match(T__39);
						setState(639);
						match(Identifier);
						}
						break;
					}
					} 
				}
				setState(644);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public TerminalNode BooleanLiteral() { return getToken(SolidityParser.BooleanLiteral, 0); }
		public NumberLiteralContext numberLiteral() {
			return getRuleContext(NumberLiteralContext.class,0);
		}
		public TerminalNode HexLiteral() { return getToken(SolidityParser.HexLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(SolidityParser.StringLiteral, 0); }
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public ElementaryTypeNameExpressionContext elementaryTypeNameExpression() {
			return getRuleContext(ElementaryTypeNameExpressionContext.class,0);
		}
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitPrimaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitPrimaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_primaryExpression);
		try {
			setState(652);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__37:
				enterOuterAlt(_localctx, 1);
				{
				setState(645);
				arrayLiteral();
				}
				break;
			case BooleanLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(646);
				match(BooleanLiteral);
				}
				break;
			case DecimalNumber:
			case HexNumber:
				enterOuterAlt(_localctx, 3);
				{
				setState(647);
				numberLiteral();
				}
				break;
			case HexLiteral:
				enterOuterAlt(_localctx, 4);
				{
				setState(648);
				match(HexLiteral);
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 5);
				{
				setState(649);
				match(StringLiteral);
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 6);
				{
				setState(650);
				match(Identifier);
				}
				break;
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case Int:
			case Uint:
			case Byte:
			case Fixed:
			case Ufixed:
				enterOuterAlt(_localctx, 7);
				{
				setState(651);
				elementaryTypeNameExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(654);
			expression(0);
			setState(659);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(655);
				match(T__12);
				setState(656);
				expression(0);
				}
				}
				setState(661);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameValueListContext extends ParserRuleContext {
		public List<NameValueContext> nameValue() {
			return getRuleContexts(NameValueContext.class);
		}
		public NameValueContext nameValue(int i) {
			return getRuleContext(NameValueContext.class,i);
		}
		public NameValueListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nameValueList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterNameValueList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitNameValueList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitNameValueList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameValueListContext nameValueList() throws RecognitionException {
		NameValueListContext _localctx = new NameValueListContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_nameValueList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(662);
			nameValue();
			setState(667);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(663);
					match(T__12);
					setState(664);
					nameValue();
					}
					} 
				}
				setState(669);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
			}
			setState(671);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(670);
				match(T__12);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameValueContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NameValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nameValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterNameValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitNameValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitNameValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameValueContext nameValue() throws RecognitionException {
		NameValueContext _localctx = new NameValueContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_nameValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(673);
			match(Identifier);
			setState(674);
			match(T__79);
			setState(675);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public FunctionCallArgumentsContext functionCallArguments() {
			return getRuleContext(FunctionCallArgumentsContext.class,0);
		}
		public List<TerminalNode> Identifier() { return getTokens(SolidityParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SolidityParser.Identifier, i);
		}
		public NewExpressionContext newExpression() {
			return getRuleContext(NewExpressionContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(680);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				{
				setState(677);
				match(Identifier);
				}
				break;
			case 2:
				{
				setState(678);
				newExpression();
				}
				break;
			case 3:
				{
				setState(679);
				typeName(0);
				}
				break;
			}
			setState(690);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__37 || _la==T__39) {
				{
				setState(688);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__39:
					{
					{
					setState(682);
					match(T__39);
					setState(683);
					match(Identifier);
					}
					}
					break;
				case T__37:
					{
					{
					setState(684);
					match(T__37);
					setState(685);
					expression(0);
					setState(686);
					match(T__38);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(692);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(693);
			match(T__18);
			setState(694);
			functionCallArguments();
			setState(695);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallArgumentsContext extends ParserRuleContext {
		public NameValueListContext nameValueList() {
			return getRuleContext(NameValueListContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public FunctionCallArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCallArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterFunctionCallArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitFunctionCallArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitFunctionCallArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallArgumentsContext functionCallArguments() throws RecognitionException {
		FunctionCallArgumentsContext _localctx = new FunctionCallArgumentsContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_functionCallArguments);
		int _la;
		try {
			setState(705);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(697);
				match(T__11);
				setState(699);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(698);
					nameValueList();
					}
				}

				setState(701);
				match(T__13);
				}
				break;
			case T__18:
			case T__19:
			case T__29:
			case T__37:
			case T__40:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__61:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case Int:
			case Uint:
			case Byte:
			case Fixed:
			case Ufixed:
			case BooleanLiteral:
			case DecimalNumber:
			case HexNumber:
			case HexLiteral:
			case Identifier:
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(703);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__29) | (1L << T__37) | (1L << T__40) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (Int - 64)) | (1L << (Uint - 64)) | (1L << (Byte - 64)) | (1L << (Fixed - 64)) | (1L << (Ufixed - 64)) | (1L << (BooleanLiteral - 64)) | (1L << (DecimalNumber - 64)) | (1L << (HexNumber - 64)) | (1L << (HexLiteral - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
					{
					setState(702);
					expressionList();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewExpressionContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public NewExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterNewExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitNewExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitNewExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewExpressionContext newExpression() throws RecognitionException {
		NewExpressionContext _localctx = new NewExpressionContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_newExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(707);
			match(T__60);
			setState(708);
			typeName(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InlineAssemblyBlockContext extends ParserRuleContext {
		public List<AssemblyItemContext> assemblyItem() {
			return getRuleContexts(AssemblyItemContext.class);
		}
		public AssemblyItemContext assemblyItem(int i) {
			return getRuleContext(AssemblyItemContext.class,i);
		}
		public InlineAssemblyBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineAssemblyBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterInlineAssemblyBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitInlineAssemblyBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitInlineAssemblyBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InlineAssemblyBlockContext inlineAssemblyBlock() throws RecognitionException {
		InlineAssemblyBlockContext _localctx = new InlineAssemblyBlockContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_inlineAssemblyBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(710);
			match(T__11);
			setState(714);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11 || ((((_la - 91)) & ~0x3f) == 0 && ((1L << (_la - 91)) & ((1L << (T__90 - 91)) | (1L << (T__92 - 91)) | (1L << (DecimalNumber - 91)) | (1L << (HexNumber - 91)) | (1L << (HexLiteral - 91)) | (1L << (Identifier - 91)) | (1L << (StringLiteral - 91)))) != 0)) {
				{
				{
				setState(711);
				assemblyItem();
				}
				}
				setState(716);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(717);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssemblyItemContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public FunctionalAssemblyExpressionContext functionalAssemblyExpression() {
			return getRuleContext(FunctionalAssemblyExpressionContext.class,0);
		}
		public InlineAssemblyBlockContext inlineAssemblyBlock() {
			return getRuleContext(InlineAssemblyBlockContext.class,0);
		}
		public AssemblyLocalBindingContext assemblyLocalBinding() {
			return getRuleContext(AssemblyLocalBindingContext.class,0);
		}
		public AssemblyAssignmentContext assemblyAssignment() {
			return getRuleContext(AssemblyAssignmentContext.class,0);
		}
		public NumberLiteralContext numberLiteral() {
			return getRuleContext(NumberLiteralContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(SolidityParser.StringLiteral, 0); }
		public TerminalNode HexLiteral() { return getToken(SolidityParser.HexLiteral, 0); }
		public AssemblyItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assemblyItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterAssemblyItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitAssemblyItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitAssemblyItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssemblyItemContext assemblyItem() throws RecognitionException {
		AssemblyItemContext _localctx = new AssemblyItemContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_assemblyItem);
		try {
			setState(727);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(719);
				match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(720);
				functionalAssemblyExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(721);
				inlineAssemblyBlock();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(722);
				assemblyLocalBinding();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(723);
				assemblyAssignment();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(724);
				numberLiteral();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(725);
				match(StringLiteral);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(726);
				match(HexLiteral);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssemblyLocalBindingContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public FunctionalAssemblyExpressionContext functionalAssemblyExpression() {
			return getRuleContext(FunctionalAssemblyExpressionContext.class,0);
		}
		public AssemblyLocalBindingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assemblyLocalBinding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterAssemblyLocalBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitAssemblyLocalBinding(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitAssemblyLocalBinding(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssemblyLocalBindingContext assemblyLocalBinding() throws RecognitionException {
		AssemblyLocalBindingContext _localctx = new AssemblyLocalBindingContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_assemblyLocalBinding);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(729);
			match(T__90);
			setState(730);
			match(Identifier);
			setState(731);
			match(T__91);
			setState(732);
			functionalAssemblyExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssemblyAssignmentContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public FunctionalAssemblyExpressionContext functionalAssemblyExpression() {
			return getRuleContext(FunctionalAssemblyExpressionContext.class,0);
		}
		public AssemblyAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assemblyAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterAssemblyAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitAssemblyAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitAssemblyAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssemblyAssignmentContext assemblyAssignment() throws RecognitionException {
		AssemblyAssignmentContext _localctx = new AssemblyAssignmentContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_assemblyAssignment);
		try {
			setState(739);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(734);
				match(Identifier);
				setState(735);
				match(T__91);
				setState(736);
				functionalAssemblyExpression();
				}
				break;
			case T__92:
				enterOuterAlt(_localctx, 2);
				{
				setState(737);
				match(T__92);
				setState(738);
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionalAssemblyExpressionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SolidityParser.Identifier, 0); }
		public List<AssemblyItemContext> assemblyItem() {
			return getRuleContexts(AssemblyItemContext.class);
		}
		public AssemblyItemContext assemblyItem(int i) {
			return getRuleContext(AssemblyItemContext.class,i);
		}
		public FunctionalAssemblyExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionalAssemblyExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterFunctionalAssemblyExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitFunctionalAssemblyExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitFunctionalAssemblyExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionalAssemblyExpressionContext functionalAssemblyExpression() throws RecognitionException {
		FunctionalAssemblyExpressionContext _localctx = new FunctionalAssemblyExpressionContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_functionalAssemblyExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(741);
			match(Identifier);
			setState(742);
			match(T__18);
			setState(744);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11 || ((((_la - 91)) & ~0x3f) == 0 && ((1L << (_la - 91)) & ((1L << (T__90 - 91)) | (1L << (T__92 - 91)) | (1L << (DecimalNumber - 91)) | (1L << (HexNumber - 91)) | (1L << (HexLiteral - 91)) | (1L << (Identifier - 91)) | (1L << (StringLiteral - 91)))) != 0)) {
				{
				setState(743);
				assemblyItem();
				}
			}

			setState(750);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(746);
				match(T__12);
				setState(747);
				assemblyItem();
				}
				}
				setState(752);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(753);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiteralContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitArrayLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_arrayLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(755);
			match(T__37);
			setState(757);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__29) | (1L << T__37) | (1L << T__40) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (Int - 64)) | (1L << (Uint - 64)) | (1L << (Byte - 64)) | (1L << (Fixed - 64)) | (1L << (Ufixed - 64)) | (1L << (BooleanLiteral - 64)) | (1L << (DecimalNumber - 64)) | (1L << (HexNumber - 64)) | (1L << (HexLiteral - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				setState(756);
				expression(0);
				}
			}

			setState(763);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(759);
				match(T__12);
				setState(760);
				expression(0);
				}
				}
				setState(765);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(766);
			match(T__38);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementaryTypeNameExpressionContext extends ParserRuleContext {
		public ElementaryTypeNameContext elementaryTypeName() {
			return getRuleContext(ElementaryTypeNameContext.class,0);
		}
		public ElementaryTypeNameExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementaryTypeNameExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterElementaryTypeNameExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitElementaryTypeNameExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitElementaryTypeNameExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementaryTypeNameExpressionContext elementaryTypeNameExpression() throws RecognitionException {
		ElementaryTypeNameExpressionContext _localctx = new ElementaryTypeNameExpressionContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_elementaryTypeNameExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(768);
			elementaryTypeName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberLiteralContext extends ParserRuleContext {
		public TerminalNode DecimalNumber() { return getToken(SolidityParser.DecimalNumber, 0); }
		public TerminalNode HexNumber() { return getToken(SolidityParser.HexNumber, 0); }
		public TerminalNode NumberUnit() { return getToken(SolidityParser.NumberUnit, 0); }
		public NumberLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).enterNumberLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SolidityListener ) ((SolidityListener)listener).exitNumberLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SolidityVisitor ) return ((SolidityVisitor<? extends T>)visitor).visitNumberLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberLiteralContext numberLiteral() throws RecognitionException {
		NumberLiteralContext _localctx = new NumberLiteralContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_numberLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(770);
			_la = _input.LA(1);
			if ( !(_la==DecimalNumber || _la==HexNumber) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(772);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				setState(771);
				match(NumberUnit);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 24:
			return typeName_sempred((TypeNameContext)_localctx, predIndex);
		case 46:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean typeName_sempred(TypeNameContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 14);
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 11);
		case 5:
			return precpred(_ctx, 10);
		case 6:
			return precpred(_ctx, 9);
		case 7:
			return precpred(_ctx, 8);
		case 8:
			return precpred(_ctx, 7);
		case 9:
			return precpred(_ctx, 6);
		case 10:
			return precpred(_ctx, 5);
		case 11:
			return precpred(_ctx, 4);
		case 12:
			return precpred(_ctx, 3);
		case 13:
			return precpred(_ctx, 2);
		case 14:
			return precpred(_ctx, 25);
		case 15:
			return precpred(_ctx, 23);
		case 16:
			return precpred(_ctx, 21);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3p\u0309\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\3\2\3\2\3\2\7\2\u0082\n\2\f\2\16\2\u0085\13\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\5\5\u0092\n\5\3\6\3\6\5\6\u0096\n\6\3"+
		"\7\3\7\3\b\5\b\u009b\n\b\3\b\3\b\3\t\3\t\3\t\5\t\u00a2\n\t\3\n\3\n\3\n"+
		"\3\n\5\n\u00a8\n\n\3\n\3\n\3\n\3\n\3\n\5\n\u00af\n\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\7\n\u00b9\n\n\f\n\16\n\u00bc\13\n\3\n\3\n\3\n\3\n\3\n\5"+
		"\n\u00c3\n\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00cb\n\13\f\13\16\13"+
		"\u00ce\13\13\5\13\u00d0\n\13\3\13\3\13\7\13\u00d4\n\13\f\13\16\13\u00d7"+
		"\13\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\7\f\u00e0\n\f\f\f\16\f\u00e3\13\f"+
		"\3\f\3\f\5\f\u00e7\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00f0\n\r\3\16"+
		"\3\16\7\16\u00f4\n\16\f\16\16\16\u00f7\13\16\3\16\3\16\3\16\5\16\u00fc"+
		"\n\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\5\17\u0105\n\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0111\n\20\f\20\16\20\u0114\13"+
		"\20\5\20\u0116\n\20\3\20\3\20\3\21\3\21\3\21\5\21\u011d\n\21\3\21\3\21"+
		"\3\22\3\22\5\22\u0123\n\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\7\22\u012e\n\22\f\22\16\22\u0131\13\22\3\22\3\22\5\22\u0135\n\22\3\22"+
		"\3\22\5\22\u0139\n\22\3\23\3\23\3\23\3\23\5\23\u013f\n\23\3\23\3\23\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\5\25\u0149\n\25\3\25\3\25\7\25\u014d\n\25"+
		"\f\25\16\25\u0150\13\25\3\25\3\25\3\26\3\26\3\26\5\26\u0157\n\26\3\26"+
		"\5\26\u015a\n\26\3\26\3\26\3\26\5\26\u015f\n\26\3\26\5\26\u0162\n\26\7"+
		"\26\u0164\n\26\f\26\16\26\u0167\13\26\5\26\u0169\n\26\3\26\3\26\3\27\3"+
		"\27\3\27\5\27\u0170\n\27\3\27\3\27\3\27\5\27\u0175\n\27\7\27\u0177\n\27"+
		"\f\27\16\27\u017a\13\27\5\27\u017c\n\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\7\30\u0184\n\30\f\30\16\30\u0187\13\30\5\30\u0189\n\30\3\30\3\30\3\31"+
		"\3\31\5\31\u018f\n\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\5\32\u0198\n"+
		"\32\3\32\3\32\3\32\5\32\u019d\n\32\3\32\7\32\u01a0\n\32\f\32\16\32\u01a3"+
		"\13\32\3\33\3\33\3\33\7\33\u01a8\n\33\f\33\16\33\u01ab\13\33\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\7\35\u01b7\n\35\f\35\16\35\u01ba"+
		"\13\35\3\35\3\35\5\35\u01be\n\35\3\36\3\36\3\37\3\37\7\37\u01c4\n\37\f"+
		"\37\16\37\u01c7\13\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \5"+
		" \u01d7\n \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u01e3\n\"\3#\3#\3"+
		"#\3#\3#\3#\3$\3$\3$\3%\3%\5%\u01f0\n%\3&\3&\3&\3&\3&\5&\u01f7\n&\3&\3"+
		"&\3&\5&\u01fc\n&\3&\5&\u01ff\n&\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\3(\3(\3"+
		"(\3(\3(\3)\3)\3)\3*\3*\3*\3+\3+\5+\u0217\n+\3+\3+\3,\3,\3,\3-\3-\3-\5"+
		"-\u0221\n-\3-\3-\5-\u0225\n-\3-\3-\3.\3.\5.\u022b\n.\3.\7.\u022e\n.\f"+
		".\16.\u0231\13.\3.\5.\u0234\n.\3.\3.\3/\3/\3\60\3\60\3\60\3\60\3\60\3"+
		"\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\5"+
		"\60\u024d\n\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\7\60"+
		"\u0283\n\60\f\60\16\60\u0286\13\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61"+
		"\5\61\u028f\n\61\3\62\3\62\3\62\7\62\u0294\n\62\f\62\16\62\u0297\13\62"+
		"\3\63\3\63\3\63\7\63\u029c\n\63\f\63\16\63\u029f\13\63\3\63\5\63\u02a2"+
		"\n\63\3\64\3\64\3\64\3\64\3\65\3\65\3\65\5\65\u02ab\n\65\3\65\3\65\3\65"+
		"\3\65\3\65\3\65\7\65\u02b3\n\65\f\65\16\65\u02b6\13\65\3\65\3\65\3\65"+
		"\3\65\3\66\3\66\5\66\u02be\n\66\3\66\3\66\5\66\u02c2\n\66\5\66\u02c4\n"+
		"\66\3\67\3\67\3\67\38\38\78\u02cb\n8\f8\168\u02ce\138\38\38\39\39\39\3"+
		"9\39\39\39\39\59\u02da\n9\3:\3:\3:\3:\3:\3;\3;\3;\3;\3;\5;\u02e6\n;\3"+
		"<\3<\3<\5<\u02eb\n<\3<\3<\7<\u02ef\n<\f<\16<\u02f2\13<\3<\3<\3=\3=\5="+
		"\u02f8\n=\3=\3=\7=\u02fc\n=\f=\16=\u02ff\13=\3=\3=\3>\3>\3?\3?\5?\u0307"+
		"\n?\3?\2\4\62^@\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|\2\22\3\2\5\t\4\2\f\fll\3\2"+
		"\21\23\3\2\27\32\5\2\30\30\32\32!\"\3\2-.\4\29<`d\3\2=>\3\2@A\3\2BC\4"+
		"\2\f\fGH\3\2IJ\3\2\6\t\3\2MN\4\2\33\33S\\\3\2gh\u0357\2\u0083\3\2\2\2"+
		"\4\u0088\3\2\2\2\6\u008d\3\2\2\2\b\u0091\3\2\2\2\n\u0093\3\2\2\2\f\u0097"+
		"\3\2\2\2\16\u009a\3\2\2\2\20\u009e\3\2\2\2\22\u00c2\3\2\2\2\24\u00c4\3"+
		"\2\2\2\26\u00da\3\2\2\2\30\u00ef\3\2\2\2\32\u00f1\3\2\2\2\34\u00ff\3\2"+
		"\2\2\36\u0108\3\2\2\2 \u0119\3\2\2\2\"\u0120\3\2\2\2$\u013a\3\2\2\2&\u0142"+
		"\3\2\2\2(\u0144\3\2\2\2*\u0153\3\2\2\2,\u016c\3\2\2\2.\u017f\3\2\2\2\60"+
		"\u018c\3\2\2\2\62\u0197\3\2\2\2\64\u01a4\3\2\2\2\66\u01ac\3\2\2\28\u01b3"+
		"\3\2\2\2:\u01bf\3\2\2\2<\u01c1\3\2\2\2>\u01d6\3\2\2\2@\u01d8\3\2\2\2B"+
		"\u01db\3\2\2\2D\u01e4\3\2\2\2F\u01ea\3\2\2\2H\u01ef\3\2\2\2J\u01f1\3\2"+
		"\2\2L\u0203\3\2\2\2N\u0206\3\2\2\2P\u020e\3\2\2\2R\u0211\3\2\2\2T\u0214"+
		"\3\2\2\2V\u021a\3\2\2\2X\u0220\3\2\2\2Z\u0228\3\2\2\2\\\u0237\3\2\2\2"+
		"^\u024c\3\2\2\2`\u028e\3\2\2\2b\u0290\3\2\2\2d\u0298\3\2\2\2f\u02a3\3"+
		"\2\2\2h\u02aa\3\2\2\2j\u02c3\3\2\2\2l\u02c5\3\2\2\2n\u02c8\3\2\2\2p\u02d9"+
		"\3\2\2\2r\u02db\3\2\2\2t\u02e5\3\2\2\2v\u02e7\3\2\2\2x\u02f5\3\2\2\2z"+
		"\u0302\3\2\2\2|\u0304\3\2\2\2~\u0082\5\4\3\2\177\u0082\5\22\n\2\u0080"+
		"\u0082\5\24\13\2\u0081~\3\2\2\2\u0081\177\3\2\2\2\u0081\u0080\3\2\2\2"+
		"\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0086"+
		"\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0087\7\2\2\3\u0087\3\3\2\2\2\u0088"+
		"\u0089\7\3\2\2\u0089\u008a\5\6\4\2\u008a\u008b\5\b\5\2\u008b\u008c\7\4"+
		"\2\2\u008c\5\3\2\2\2\u008d\u008e\7l\2\2\u008e\7\3\2\2\2\u008f\u0092\5"+
		"\n\6\2\u0090\u0092\5^\60\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092"+
		"\t\3\2\2\2\u0093\u0095\5\16\b\2\u0094\u0096\5\16\b\2\u0095\u0094\3\2\2"+
		"\2\u0095\u0096\3\2\2\2\u0096\13\3\2\2\2\u0097\u0098\t\2\2\2\u0098\r\3"+
		"\2\2\2\u0099\u009b\5\f\7\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009d\7e\2\2\u009d\17\3\2\2\2\u009e\u00a1\7l\2\2"+
		"\u009f\u00a0\7\n\2\2\u00a0\u00a2\7l\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2"+
		"\3\2\2\2\u00a2\21\3\2\2\2\u00a3\u00a4\7\13\2\2\u00a4\u00a7\7m\2\2\u00a5"+
		"\u00a6\7\n\2\2\u00a6\u00a8\7l\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2"+
		"\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00c3\7\4\2\2\u00aa\u00ab\7\13\2\2\u00ab"+
		"\u00ae\t\3\2\2\u00ac\u00ad\7\n\2\2\u00ad\u00af\7l\2\2\u00ae\u00ac\3\2"+
		"\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\7\r\2\2\u00b1"+
		"\u00b2\7m\2\2\u00b2\u00c3\7\4\2\2\u00b3\u00b4\7\13\2\2\u00b4\u00b5\7\16"+
		"\2\2\u00b5\u00ba\5\20\t\2\u00b6\u00b7\7\17\2\2\u00b7\u00b9\5\20\t\2\u00b8"+
		"\u00b6\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2"+
		"\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00be\7\20\2\2\u00be"+
		"\u00bf\7\r\2\2\u00bf\u00c0\7m\2\2\u00c0\u00c1\7\4\2\2\u00c1\u00c3\3\2"+
		"\2\2\u00c2\u00a3\3\2\2\2\u00c2\u00aa\3\2\2\2\u00c2\u00b3\3\2\2\2\u00c3"+
		"\23\3\2\2\2\u00c4\u00c5\t\4\2\2\u00c5\u00cf\7l\2\2\u00c6\u00c7\7\24\2"+
		"\2\u00c7\u00cc\5\26\f\2\u00c8\u00c9\7\17\2\2\u00c9\u00cb\5\26\f\2\u00ca"+
		"\u00c8\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2"+
		"\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00c6\3\2\2\2\u00cf"+
		"\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d5\7\16\2\2\u00d2\u00d4\5"+
		"\30\r\2\u00d3\u00d2\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5"+
		"\u00d6\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d9\7\20"+
		"\2\2\u00d9\25\3\2\2\2\u00da\u00e6\5\64\33\2\u00db\u00dc\7\25\2\2\u00dc"+
		"\u00e1\5^\60\2\u00dd\u00de\7\17\2\2\u00de\u00e0\5^\60\2\u00df\u00dd\3"+
		"\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2"+
		"\u00e4\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\7\26\2\2\u00e5\u00e7\3"+
		"\2\2\2\u00e6\u00db\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\27\3\2\2\2\u00e8"+
		"\u00f0\5\32\16\2\u00e9\u00f0\5\34\17\2\u00ea\u00f0\5\36\20\2\u00eb\u00f0"+
		"\5 \21\2\u00ec\u00f0\5\"\22\2\u00ed\u00f0\5$\23\2\u00ee\u00f0\5(\25\2"+
		"\u00ef\u00e8\3\2\2\2\u00ef\u00e9\3\2\2\2\u00ef\u00ea\3\2\2\2\u00ef\u00eb"+
		"\3\2\2\2\u00ef\u00ec\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00ee\3\2\2\2\u00f0"+
		"\31\3\2\2\2\u00f1\u00f5\5\62\32\2\u00f2\u00f4\t\5\2\2\u00f3\u00f2\3\2"+
		"\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6"+
		"\u00f8\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00fb\7l\2\2\u00f9\u00fa\7\33"+
		"\2\2\u00fa\u00fc\5^\60\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc"+
		"\u00fd\3\2\2\2\u00fd\u00fe\7\4\2\2\u00fe\33\3\2\2\2\u00ff\u0100\7\34\2"+
		"\2\u0100\u0101\7l\2\2\u0101\u0104\7\35\2\2\u0102\u0105\7\f\2\2\u0103\u0105"+
		"\5\62\32\2\u0104\u0102\3\2\2\2\u0104\u0103\3\2\2\2\u0105\u0106\3\2\2\2"+
		"\u0106\u0107\7\4\2\2\u0107\35\3\2\2\2\u0108\u0109\7\36\2\2\u0109\u010a"+
		"\7l\2\2\u010a\u0115\7\16\2\2\u010b\u010c\5\60\31\2\u010c\u0112\7\4\2\2"+
		"\u010d\u010e\5\60\31\2\u010e\u010f\7\4\2\2\u010f\u0111\3\2\2\2\u0110\u010d"+
		"\3\2\2\2\u0111\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0116\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u010b\3\2\2\2\u0115\u0116\3\2"+
		"\2\2\u0116\u0117\3\2\2\2\u0117\u0118\7\20\2\2\u0118\37\3\2\2\2\u0119\u011a"+
		"\7\37\2\2\u011a\u011c\7l\2\2\u011b\u011d\5,\27\2\u011c\u011b\3\2\2\2\u011c"+
		"\u011d\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011f\5<\37\2\u011f!\3\2\2\2"+
		"\u0120\u0122\7 \2\2\u0121\u0123\7l\2\2\u0122\u0121\3\2\2\2\u0122\u0123"+
		"\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u012f\5,\27\2\u0125\u012e\5h\65\2\u0126"+
		"\u012e\7l\2\2\u0127\u012e\7\32\2\2\u0128\u012e\7!\2\2\u0129\u012e\7\""+
		"\2\2\u012a\u012e\7\27\2\2\u012b\u012e\7\30\2\2\u012c\u012e\7\31\2\2\u012d"+
		"\u0125\3\2\2\2\u012d\u0126\3\2\2\2\u012d\u0127\3\2\2\2\u012d\u0128\3\2"+
		"\2\2\u012d\u0129\3\2\2\2\u012d\u012a\3\2\2\2\u012d\u012b\3\2\2\2\u012d"+
		"\u012c\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2"+
		"\2\2\u0130\u0134\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0133\7#\2\2\u0133"+
		"\u0135\5,\27\2\u0134\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0138\3\2"+
		"\2\2\u0136\u0139\7\4\2\2\u0137\u0139\5<\37\2\u0138\u0136\3\2\2\2\u0138"+
		"\u0137\3\2\2\2\u0139#\3\2\2\2\u013a\u013b\7$\2\2\u013b\u013c\7l\2\2\u013c"+
		"\u013e\5*\26\2\u013d\u013f\7%\2\2\u013e\u013d\3\2\2\2\u013e\u013f\3\2"+
		"\2\2\u013f\u0140\3\2\2\2\u0140\u0141\7\4\2\2\u0141%\3\2\2\2\u0142\u0143"+
		"\7l\2\2\u0143\'\3\2\2\2\u0144\u0145\7&\2\2\u0145\u0146\7l\2\2\u0146\u0148"+
		"\7\16\2\2\u0147\u0149\5&\24\2\u0148\u0147\3\2\2\2\u0148\u0149\3\2\2\2"+
		"\u0149\u014e\3\2\2\2\u014a\u014b\7\17\2\2\u014b\u014d\5&\24\2\u014c\u014a"+
		"\3\2\2\2\u014d\u0150\3\2\2\2\u014e\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f"+
		"\u0151\3\2\2\2\u0150\u014e\3\2\2\2\u0151\u0152\7\20\2\2\u0152)\3\2\2\2"+
		"\u0153\u0168\7\25\2\2\u0154\u0156\5\62\32\2\u0155\u0157\7\'\2\2\u0156"+
		"\u0155\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0159\3\2\2\2\u0158\u015a\7l"+
		"\2\2\u0159\u0158\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u0165\3\2\2\2\u015b"+
		"\u015c\7\17\2\2\u015c\u015e\5\62\32\2\u015d\u015f\7\'\2\2\u015e\u015d"+
		"\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0161\3\2\2\2\u0160\u0162\7l\2\2\u0161"+
		"\u0160\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0164\3\2\2\2\u0163\u015b\3\2"+
		"\2\2\u0164\u0167\3\2\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166"+
		"\u0169\3\2\2\2\u0167\u0165\3\2\2\2\u0168\u0154\3\2\2\2\u0168\u0169\3\2"+
		"\2\2\u0169\u016a\3\2\2\2\u016a\u016b\7\26\2\2\u016b+\3\2\2\2\u016c\u017b"+
		"\7\25\2\2\u016d\u016f\5\62\32\2\u016e\u0170\7l\2\2\u016f\u016e\3\2\2\2"+
		"\u016f\u0170\3\2\2\2\u0170\u0178\3\2\2\2\u0171\u0172\7\17\2\2\u0172\u0174"+
		"\5\62\32\2\u0173\u0175\7l\2\2\u0174\u0173\3\2\2\2\u0174\u0175\3\2\2\2"+
		"\u0175\u0177\3\2\2\2\u0176\u0171\3\2\2\2\u0177\u017a\3\2\2\2\u0178\u0176"+
		"\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u017c\3\2\2\2\u017a\u0178\3\2\2\2\u017b"+
		"\u016d\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017e\7\26"+
		"\2\2\u017e-\3\2\2\2\u017f\u0188\7\25\2\2\u0180\u0185\5\62\32\2\u0181\u0182"+
		"\7\17\2\2\u0182\u0184\5\62\32\2\u0183\u0181\3\2\2\2\u0184\u0187\3\2\2"+
		"\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0189\3\2\2\2\u0187\u0185"+
		"\3\2\2\2\u0188\u0180\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u018a\3\2\2\2\u018a"+
		"\u018b\7\26\2\2\u018b/\3\2\2\2\u018c\u018e\5\62\32\2\u018d\u018f\5:\36"+
		"\2\u018e\u018d\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u0190\3\2\2\2\u0190\u0191"+
		"\7l\2\2\u0191\61\3\2\2\2\u0192\u0193\b\32\1\2\u0193\u0198\5\\/\2\u0194"+
		"\u0198\5\64\33\2\u0195\u0198\5\66\34\2\u0196\u0198\58\35\2\u0197\u0192"+
		"\3\2\2\2\u0197\u0194\3\2\2\2\u0197\u0195\3\2\2\2\u0197\u0196\3\2\2\2\u0198"+
		"\u01a1\3\2\2\2\u0199\u019a\f\4\2\2\u019a\u019c\7(\2\2\u019b\u019d\5^\60"+
		"\2\u019c\u019b\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u01a0"+
		"\7)\2\2\u019f\u0199\3\2\2\2\u01a0\u01a3\3\2\2\2\u01a1\u019f\3\2\2\2\u01a1"+
		"\u01a2\3\2\2\2\u01a2\63\3\2\2\2\u01a3\u01a1\3\2\2\2\u01a4\u01a9\7l\2\2"+
		"\u01a5\u01a6\7*\2\2\u01a6\u01a8\7l\2\2\u01a7\u01a5\3\2\2\2\u01a8\u01ab"+
		"\3\2\2\2\u01a9\u01a7\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa\65\3\2\2\2\u01ab"+
		"\u01a9\3\2\2\2\u01ac\u01ad\7+\2\2\u01ad\u01ae\7\25\2\2\u01ae\u01af\5\\"+
		"/\2\u01af\u01b0\7,\2\2\u01b0\u01b1\5\62\32\2\u01b1\u01b2\7\26\2\2\u01b2"+
		"\67\3\2\2\2\u01b3\u01b4\7 \2\2\u01b4\u01b8\5.\30\2\u01b5\u01b7\t\6\2\2"+
		"\u01b6\u01b5\3\2\2\2\u01b7\u01ba\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b8\u01b9"+
		"\3\2\2\2\u01b9\u01bd\3\2\2\2\u01ba\u01b8\3\2\2\2\u01bb\u01bc\7#\2\2\u01bc"+
		"\u01be\5.\30\2\u01bd\u01bb\3\2\2\2\u01bd\u01be\3\2\2\2\u01be9\3\2\2\2"+
		"\u01bf\u01c0\t\7\2\2\u01c0;\3\2\2\2\u01c1\u01c5\7\16\2\2\u01c2\u01c4\5"+
		"> \2\u01c3\u01c2\3\2\2\2\u01c4\u01c7\3\2\2\2\u01c5\u01c3\3\2\2\2\u01c5"+
		"\u01c6\3\2\2\2\u01c6\u01c8\3\2\2\2\u01c7\u01c5\3\2\2\2\u01c8\u01c9\7\20"+
		"\2\2\u01c9=\3\2\2\2\u01ca\u01d7\5B\"\2\u01cb\u01d7\5D#\2\u01cc\u01d7\5"+
		"J&\2\u01cd\u01d7\5<\37\2\u01ce\u01d7\5L\'\2\u01cf\u01d7\5N(\2\u01d0\u01d7"+
		"\5F$\2\u01d1\u01d7\5P)\2\u01d2\u01d7\5R*\2\u01d3\u01d7\5T+\2\u01d4\u01d7"+
		"\5V,\2\u01d5\u01d7\5H%\2\u01d6\u01ca\3\2\2\2\u01d6\u01cb\3\2\2\2\u01d6"+
		"\u01cc\3\2\2\2\u01d6\u01cd\3\2\2\2\u01d6\u01ce\3\2\2\2\u01d6\u01cf\3\2"+
		"\2\2\u01d6\u01d0\3\2\2\2\u01d6\u01d1\3\2\2\2\u01d6\u01d2\3\2\2\2\u01d6"+
		"\u01d3\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d6\u01d5\3\2\2\2\u01d7?\3\2\2\2"+
		"\u01d8\u01d9\5^\60\2\u01d9\u01da\7\4\2\2\u01daA\3\2\2\2\u01db\u01dc\7"+
		"/\2\2\u01dc\u01dd\7\25\2\2\u01dd\u01de\5^\60\2\u01de\u01df\7\26\2\2\u01df"+
		"\u01e2\5> \2\u01e0\u01e1\7\60\2\2\u01e1\u01e3\5> \2\u01e2\u01e0\3\2\2"+
		"\2\u01e2\u01e3\3\2\2\2\u01e3C\3\2\2\2\u01e4\u01e5\7\61\2\2\u01e5\u01e6"+
		"\7\25\2\2\u01e6\u01e7\5^\60\2\u01e7\u01e8\7\26\2\2\u01e8\u01e9\5> \2\u01e9"+
		"E\3\2\2\2\u01ea\u01eb\7\62\2\2\u01eb\u01ec\7\4\2\2\u01ecG\3\2\2\2\u01ed"+
		"\u01f0\5X-\2\u01ee\u01f0\5@!\2\u01ef\u01ed\3\2\2\2\u01ef\u01ee\3\2\2\2"+
		"\u01f0I\3\2\2\2\u01f1\u01f2\7\35\2\2\u01f2\u01f6\7\25\2\2\u01f3\u01f4"+
		"\5H%\2\u01f4\u01f5\7\4\2\2\u01f5\u01f7\3\2\2\2\u01f6\u01f3\3\2\2\2\u01f6"+
		"\u01f7\3\2\2\2\u01f7\u01fb\3\2\2\2\u01f8\u01f9\5^\60\2\u01f9\u01fa\7\4"+
		"\2\2\u01fa\u01fc\3\2\2\2\u01fb\u01f8\3\2\2\2\u01fb\u01fc\3\2\2\2\u01fc"+
		"\u01fe\3\2\2\2\u01fd\u01ff\5^\60\2\u01fe\u01fd\3\2\2\2\u01fe\u01ff\3\2"+
		"\2\2\u01ff\u0200\3\2\2\2\u0200\u0201\7\26\2\2\u0201\u0202\5> \2\u0202"+
		"K\3\2\2\2\u0203\u0204\7\63\2\2\u0204\u0205\5n8\2\u0205M\3\2\2\2\u0206"+
		"\u0207\7\64\2\2\u0207\u0208\5> \2\u0208\u0209\7\61\2\2\u0209\u020a\7\25"+
		"\2\2\u020a\u020b\5^\60\2\u020b\u020c\7\26\2\2\u020c\u020d\7\4\2\2\u020d"+
		"O\3\2\2\2\u020e\u020f\7\65\2\2\u020f\u0210\7\4\2\2\u0210Q\3\2\2\2\u0211"+
		"\u0212\7\66\2\2\u0212\u0213\7\4\2\2\u0213S\3\2\2\2\u0214\u0216\7\67\2"+
		"\2\u0215\u0217\5^\60\2\u0216\u0215\3\2\2\2\u0216\u0217\3\2\2\2\u0217\u0218"+
		"\3\2\2\2\u0218\u0219\7\4\2\2\u0219U\3\2\2\2\u021a\u021b\78\2\2\u021b\u021c"+
		"\7\4\2\2\u021cW\3\2\2\2\u021d\u021e\79\2\2\u021e\u0221\5Z.\2\u021f\u0221"+
		"\5\60\31\2\u0220\u021d\3\2\2\2\u0220\u021f\3\2\2\2\u0221\u0224\3\2\2\2"+
		"\u0222\u0223\7\33\2\2\u0223\u0225\5^\60\2\u0224\u0222\3\2\2\2\u0224\u0225"+
		"\3\2\2\2\u0225\u0226\3\2\2\2\u0226\u0227\7\4\2\2\u0227Y\3\2\2\2\u0228"+
		"\u022f\7\25\2\2\u0229\u022b\7l\2\2\u022a\u0229\3\2\2\2\u022a\u022b\3\2"+
		"\2\2\u022b\u022c\3\2\2\2\u022c\u022e\7\17\2\2\u022d\u022a\3\2\2\2\u022e"+
		"\u0231\3\2\2\2\u022f\u022d\3\2\2\2\u022f\u0230\3\2\2\2\u0230\u0233\3\2"+
		"\2\2\u0231\u022f\3\2\2\2\u0232\u0234\7l\2\2\u0233\u0232\3\2\2\2\u0233"+
		"\u0234\3\2\2\2\u0234\u0235\3\2\2\2\u0235\u0236\7\26\2\2\u0236[\3\2\2\2"+
		"\u0237\u0238\t\b\2\2\u0238]\3\2\2\2\u0239\u023a\b\60\1\2\u023a\u024d\5"+
		"h\65\2\u023b\u023c\7?\2\2\u023c\u024d\5\62\32\2\u023d\u023e\7\25\2\2\u023e"+
		"\u023f\5^\60\2\u023f\u0240\7\26\2\2\u0240\u024d\3\2\2\2\u0241\u0242\t"+
		"\t\2\2\u0242\u024d\5^\60\25\u0243\u0244\t\n\2\2\u0244\u024d\5^\60\24\u0245"+
		"\u0246\t\13\2\2\u0246\u024d\5^\60\23\u0247\u0248\7D\2\2\u0248\u024d\5"+
		"^\60\22\u0249\u024a\7E\2\2\u024a\u024d\5^\60\21\u024b\u024d\5`\61\2\u024c"+
		"\u0239\3\2\2\2\u024c\u023b\3\2\2\2\u024c\u023d\3\2\2\2\u024c\u0241\3\2"+
		"\2\2\u024c\u0243\3\2\2\2\u024c\u0245\3\2\2\2\u024c\u0247\3\2\2\2\u024c"+
		"\u0249\3\2\2\2\u024c\u024b\3\2\2\2\u024d\u0284\3\2\2\2\u024e\u024f\f\20"+
		"\2\2\u024f\u0250\7F\2\2\u0250\u0283\5^\60\21\u0251\u0252\f\17\2\2\u0252"+
		"\u0253\t\f\2\2\u0253\u0283\5^\60\20\u0254\u0255\f\16\2\2\u0255\u0256\t"+
		"\n\2\2\u0256\u0283\5^\60\17\u0257\u0258\f\r\2\2\u0258\u0259\t\r\2\2\u0259"+
		"\u0283\5^\60\16\u025a\u025b\f\f\2\2\u025b\u025c\7K\2\2\u025c\u0283\5^"+
		"\60\r\u025d\u025e\f\13\2\2\u025e\u025f\7\5\2\2\u025f\u0283\5^\60\f\u0260"+
		"\u0261\f\n\2\2\u0261\u0262\7L\2\2\u0262\u0283\5^\60\13\u0263\u0264\f\t"+
		"\2\2\u0264\u0265\t\16\2\2\u0265\u0283\5^\60\n\u0266\u0267\f\b\2\2\u0267"+
		"\u0268\t\17\2\2\u0268\u0283\5^\60\t\u0269\u026a\f\7\2\2\u026a\u026b\7"+
		"O\2\2\u026b\u0283\5^\60\b\u026c\u026d\f\6\2\2\u026d\u026e\7P\2\2\u026e"+
		"\u0283\5^\60\7\u026f\u0270\f\5\2\2\u0270\u0271\7Q\2\2\u0271\u0272\5^\60"+
		"\2\u0272\u0273\7R\2\2\u0273\u0274\5^\60\6\u0274\u0283\3\2\2\2\u0275\u0276"+
		"\f\4\2\2\u0276\u0277\t\20\2\2\u0277\u0283\5^\60\5\u0278\u0279\f\33\2\2"+
		"\u0279\u0283\t\t\2\2\u027a\u027b\f\31\2\2\u027b\u027c\7(\2\2\u027c\u027d"+
		"\5^\60\2\u027d\u027e\7)\2\2\u027e\u0283\3\2\2\2\u027f\u0280\f\27\2\2\u0280"+
		"\u0281\7*\2\2\u0281\u0283\7l\2\2\u0282\u024e\3\2\2\2\u0282\u0251\3\2\2"+
		"\2\u0282\u0254\3\2\2\2\u0282\u0257\3\2\2\2\u0282\u025a\3\2\2\2\u0282\u025d"+
		"\3\2\2\2\u0282\u0260\3\2\2\2\u0282\u0263\3\2\2\2\u0282\u0266\3\2\2\2\u0282"+
		"\u0269\3\2\2\2\u0282\u026c\3\2\2\2\u0282\u026f\3\2\2\2\u0282\u0275\3\2"+
		"\2\2\u0282\u0278\3\2\2\2\u0282\u027a\3\2\2\2\u0282\u027f\3\2\2\2\u0283"+
		"\u0286\3\2\2\2\u0284\u0282\3\2\2\2\u0284\u0285\3\2\2\2\u0285_\3\2\2\2"+
		"\u0286\u0284\3\2\2\2\u0287\u028f\5x=\2\u0288\u028f\7f\2\2\u0289\u028f"+
		"\5|?\2\u028a\u028f\7j\2\2\u028b\u028f\7m\2\2\u028c\u028f\7l\2\2\u028d"+
		"\u028f\5z>\2\u028e\u0287\3\2\2\2\u028e\u0288\3\2\2\2\u028e\u0289\3\2\2"+
		"\2\u028e\u028a\3\2\2\2\u028e\u028b\3\2\2\2\u028e\u028c\3\2\2\2\u028e\u028d"+
		"\3\2\2\2\u028fa\3\2\2\2\u0290\u0295\5^\60\2\u0291\u0292\7\17\2\2\u0292"+
		"\u0294\5^\60\2\u0293\u0291\3\2\2\2\u0294\u0297\3\2\2\2\u0295\u0293\3\2"+
		"\2\2\u0295\u0296\3\2\2\2\u0296c\3\2\2\2\u0297\u0295\3\2\2\2\u0298\u029d"+
		"\5f\64\2\u0299\u029a\7\17\2\2\u029a\u029c\5f\64\2\u029b\u0299\3\2\2\2"+
		"\u029c\u029f\3\2\2\2\u029d\u029b\3\2\2\2\u029d\u029e\3\2\2\2\u029e\u02a1"+
		"\3\2\2\2\u029f\u029d\3\2\2\2\u02a0\u02a2\7\17\2\2\u02a1\u02a0\3\2\2\2"+
		"\u02a1\u02a2\3\2\2\2\u02a2e\3\2\2\2\u02a3\u02a4\7l\2\2\u02a4\u02a5\7R"+
		"\2\2\u02a5\u02a6\5^\60\2\u02a6g\3\2\2\2\u02a7\u02ab\7l\2\2\u02a8\u02ab"+
		"\5l\67\2\u02a9\u02ab\5\62\32\2\u02aa\u02a7\3\2\2\2\u02aa\u02a8\3\2\2\2"+
		"\u02aa\u02a9\3\2\2\2\u02ab\u02b4\3\2\2\2\u02ac\u02ad\7*\2\2\u02ad\u02b3"+
		"\7l\2\2\u02ae\u02af\7(\2\2\u02af\u02b0\5^\60\2\u02b0\u02b1\7)\2\2\u02b1"+
		"\u02b3\3\2\2\2\u02b2\u02ac\3\2\2\2\u02b2\u02ae\3\2\2\2\u02b3\u02b6\3\2"+
		"\2\2\u02b4\u02b2\3\2\2\2\u02b4\u02b5\3\2\2\2\u02b5\u02b7\3\2\2\2\u02b6"+
		"\u02b4\3\2\2\2\u02b7\u02b8\7\25\2\2\u02b8\u02b9\5j\66\2\u02b9\u02ba\7"+
		"\26\2\2\u02bai\3\2\2\2\u02bb\u02bd\7\16\2\2\u02bc\u02be\5d\63\2\u02bd"+
		"\u02bc\3\2\2\2\u02bd\u02be\3\2\2\2\u02be\u02bf\3\2\2\2\u02bf\u02c4\7\20"+
		"\2\2\u02c0\u02c2\5b\62\2\u02c1\u02c0\3\2\2\2\u02c1\u02c2\3\2\2\2\u02c2"+
		"\u02c4\3\2\2\2\u02c3\u02bb\3\2\2\2\u02c3\u02c1\3\2\2\2\u02c4k\3\2\2\2"+
		"\u02c5\u02c6\7?\2\2\u02c6\u02c7\5\62\32\2\u02c7m\3\2\2\2\u02c8\u02cc\7"+
		"\16\2\2\u02c9\u02cb\5p9\2\u02ca\u02c9\3\2\2\2\u02cb\u02ce\3\2\2\2\u02cc"+
		"\u02ca\3\2\2\2\u02cc\u02cd\3\2\2\2\u02cd\u02cf\3\2\2\2\u02ce\u02cc\3\2"+
		"\2\2\u02cf\u02d0\7\20\2\2\u02d0o\3\2\2\2\u02d1\u02da\7l\2\2\u02d2\u02da"+
		"\5v<\2\u02d3\u02da\5n8\2\u02d4\u02da\5r:\2\u02d5\u02da\5t;\2\u02d6\u02da"+
		"\5|?\2\u02d7\u02da\7m\2\2\u02d8\u02da\7j\2\2\u02d9\u02d1\3\2\2\2\u02d9"+
		"\u02d2\3\2\2\2\u02d9\u02d3\3\2\2\2\u02d9\u02d4\3\2\2\2\u02d9\u02d5\3\2"+
		"\2\2\u02d9\u02d6\3\2\2\2\u02d9\u02d7\3\2\2\2\u02d9\u02d8\3\2\2\2\u02da"+
		"q\3\2\2\2\u02db\u02dc\7]\2\2\u02dc\u02dd\7l\2\2\u02dd\u02de\7^\2\2\u02de"+
		"\u02df\5v<\2\u02dfs\3\2\2\2\u02e0\u02e1\7l\2\2\u02e1\u02e2\7^\2\2\u02e2"+
		"\u02e6\5v<\2\u02e3\u02e4\7_\2\2\u02e4\u02e6\7l\2\2\u02e5\u02e0\3\2\2\2"+
		"\u02e5\u02e3\3\2\2\2\u02e6u\3\2\2\2\u02e7\u02e8\7l\2\2\u02e8\u02ea\7\25"+
		"\2\2\u02e9\u02eb\5p9\2\u02ea\u02e9\3\2\2\2\u02ea\u02eb\3\2\2\2\u02eb\u02f0"+
		"\3\2\2\2\u02ec\u02ed\7\17\2\2\u02ed\u02ef\5p9\2\u02ee\u02ec\3\2\2\2\u02ef"+
		"\u02f2\3\2\2\2\u02f0\u02ee\3\2\2\2\u02f0\u02f1\3\2\2\2\u02f1\u02f3\3\2"+
		"\2\2\u02f2\u02f0\3\2\2\2\u02f3\u02f4\7\26\2\2\u02f4w\3\2\2\2\u02f5\u02f7"+
		"\7(\2\2\u02f6\u02f8\5^\60\2\u02f7\u02f6\3\2\2\2\u02f7\u02f8\3\2\2\2\u02f8"+
		"\u02fd\3\2\2\2\u02f9\u02fa\7\17\2\2\u02fa\u02fc\5^\60\2\u02fb\u02f9\3"+
		"\2\2\2\u02fc\u02ff\3\2\2\2\u02fd\u02fb\3\2\2\2\u02fd\u02fe\3\2\2\2\u02fe"+
		"\u0300\3\2\2\2\u02ff\u02fd\3\2\2\2\u0300\u0301\7)\2\2\u0301y\3\2\2\2\u0302"+
		"\u0303\5\\/\2\u0303{\3\2\2\2\u0304\u0306\t\21\2\2\u0305\u0307\7i\2\2\u0306"+
		"\u0305\3\2\2\2\u0306\u0307\3\2\2\2\u0307}\3\2\2\2U\u0081\u0083\u0091\u0095"+
		"\u009a\u00a1\u00a7\u00ae\u00ba\u00c2\u00cc\u00cf\u00d5\u00e1\u00e6\u00ef"+
		"\u00f5\u00fb\u0104\u0112\u0115\u011c\u0122\u012d\u012f\u0134\u0138\u013e"+
		"\u0148\u014e\u0156\u0159\u015e\u0161\u0165\u0168\u016f\u0174\u0178\u017b"+
		"\u0185\u0188\u018e\u0197\u019c\u01a1\u01a9\u01b8\u01bd\u01c5\u01d6\u01e2"+
		"\u01ef\u01f6\u01fb\u01fe\u0216\u0220\u0224\u022a\u022f\u0233\u024c\u0282"+
		"\u0284\u028e\u0295\u029d\u02a1\u02aa\u02b2\u02b4\u02bd\u02c1\u02c3\u02cc"+
		"\u02d9\u02e5\u02ea\u02f0\u02f7\u02fd\u0306";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}