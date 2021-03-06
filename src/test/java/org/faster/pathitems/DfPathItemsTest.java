package org.faster.pathitems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;
import org.faster.pathinfo.request.FakeTokenPathInfo;
import org.junit.Test;

public class DfPathItemsTest {
	
	@Test
	public void testPathTypeFirstItem() throws IOException, ProtocolSyntaxErrorException {
		PathItems items = createPathItems();
		
		items.next();
		assertEquals(false, items.pathInfo().isDirectory());
	}
	
	@Test
	public void testPathFirstItem() throws IOException, ProtocolSyntaxErrorException {
		PathItems items = createPathItems();
		
		items.next();
		assertEquals(items.pathInfo().path(), "/audio/voice.mp3");
	}
	
	@Test
	public void testSizeFirstItem() throws IOException, ProtocolSyntaxErrorException {
		PathItems items = createPathItems();
		
		items.next();
		assertEquals(items.pathInfo().size(), 2017);
	}
	
	@Test
	public void testPathTypeSecondItem() throws IOException, ProtocolSyntaxErrorException {
		PathItems items = createPathItems();
		
		items.next();
		items.next();
		
		assertEquals(items.pathInfo().isDirectory(), true);
	}
	
	@Test
	public void testPathSecondItem() throws IOException, ProtocolSyntaxErrorException {
		PathItems items = createPathItems();
		
		items.next();
		items.next();
		
		assertEquals(items.pathInfo().path(), "/photos/family");
	}
	
	@Test
	public void testSizeSecondItem() throws IOException, ProtocolSyntaxErrorException {
		PathItems items = createPathItems();
		
		items.next();
		items.next();
		
		assertEquals(items.pathInfo().size(), -1);
	}
	
	@Test
	public void thirdNextMakesNoPath() throws IOException, ProtocolSyntaxErrorException {
		PathItems items = createPathItems();
		items.next();
		items.next();
		items.next();
		
		assertTrue(items.pathInfo() == PathInfo.NO_PATH_INFO);
	}
	
	@Test
	public void thirdNextReturnsFalse() throws IOException, ProtocolSyntaxErrorException {
		PathItems items = createPathItems();
		items.next();
		items.next();
		
		assertFalse(items.next());
	}
	
	@Test
	public void fourthNextReturnsFalse() throws IOException, ProtocolSyntaxErrorException {
		PathItems items = createPathItems();
		items.next();
		items.next();
		items.next();

		
		assertFalse(items.next());
	}
	
	@Test(expected = ProtocolSyntaxErrorException.class)
	public void throwsIfInvalidToken() throws IOException, ProtocolSyntaxErrorException {
		PathItems items = createPathItemsInvalidToken();
		items.next();
		items.next();
		items.next();
	}

	
	private PathItems createPathItems() {
		return new PiDefault(new FakeTokenPathInfo("\n"
				+ "f\n"
				+ "/audio/voice.mp3\n"
				+ "2017\n"
				+ "\n"
				+ "d\n"
				+ "/photos/family\n"
				+ "e"));
	}
	
	private PathItems createPathItemsInvalidToken() {
		return new PiDefault(new FakeTokenPathInfo("\n"
				+ "f\n"
				+ "/audio/voice.mp3\n"
				+ "2017\n"
				+ "\n"
				+ "d\n"
				+ "/photos/family\n"
				+ "p"));
	}

}
