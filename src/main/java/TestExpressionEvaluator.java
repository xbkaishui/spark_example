/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.ExpressionEvaluator;

import java.lang.reflect.InvocationTargetException;

/**
 * @author xbkaishui
 * @version $Id: TestExpressionEvaluator, v 0.1 2016 06 24 下午1:43 xbkaishui Exp $$
 */
public class TestExpressionEvaluator {
	public static void
	main(String[] args) throws CompileException, InvocationTargetException {

		// Now here's where the story begins...
		ExpressionEvaluator ee = new ExpressionEvaluator();

		// The expression will have two "int" parameters: "a" and "b".
		ee.setParameters(new String[] {"a", "b"}, new Class[] {int.class, int.class});

		// And the expression (i.e. "result") type is also "int".
		ee.setExpressionType(int.class);

		// And now we "cook" (scan, parse, compile and load) the fabulous expression.
		ee.cook("a + b");

		// Eventually we evaluate the expression - and that goes super-fast.
		int result = (Integer) ee.evaluate(new Object[] {19, 23});
		System.out.println(result);
	}

}
