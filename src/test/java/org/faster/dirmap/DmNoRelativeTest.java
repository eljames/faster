package org.faster.dirmap;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import org.faster.pathinfo.PathInfo;
import org.junit.Test;

public class DmNoRelativeTest {
	
	
	@Test
	public void returnsFalseHasRelative() {
		
		boolean has = new DmNoRelative(
			new DmFake(true)
		).has("/home/..");
		
		assertEquals(false, has);
	}
	
	@Test
	public void returnsTrueIfHasNoRelative() {
		
			boolean has = new DmNoRelative(
				new DmFake(true)
			).has("/home/user/docs");
			
			assertEquals(true, has);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void throwsExceptionIfHasRelativeInstruction() {
		new DmNoRelative(
				new DmFake(true)
		).paths("/home/user/../docs");
	}
	
	@Test
	public void dontThrowExceptionIfNotRelative() {
		new DmNoRelative(
				new DmFake(true)
		).paths("/home/user/docs");
	}
	
	class DmFake implements DirMap {
		
		private final boolean has;
		
		public DmFake(final boolean has) {
			this.has = has;
		}

		@Override
		public boolean has(CharSequence path) {
			return this.has;
		}

		@Override
		public Collection<PathInfo> paths(CharSequence path) {
			return null;
		}
		
	}

}
