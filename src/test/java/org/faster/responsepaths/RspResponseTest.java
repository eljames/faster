package org.faster.responsepaths;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.io.StringWriter;
import org.faster.dirmap.DmDefault;
import org.faster.pathinfo.request.PathItemsToken;
import org.faster.responsepaths.ResponsePaths;
import org.faster.responsepaths.RspResponse;
import org.faster.sentpath.SpDefault;
import org.faster.written.WtDefault;
import org.junit.Test;

public class RspResponseTest {
	
	@Test
	public void responsePath() throws IOException {
		
		String expected = new PathItemsToken()
				.append(false, "/media/pics/me.txt", 55)
				.append(true, "/media/pics/travel", -1)
				.append(false, "/media/pics/file_test.txt", 25)
				.builder()
				.toString();
		
		StringWriter strWriter = new StringWriter();
		
		ResponsePaths responsed = new RspResponse(
			new ResponsePaths() {
				
				@Override
				public void respond(CharSequence relativePath) throws IOException {}
			},
			new DmDefault(new CreatedPathMap().create("/org/faster/responsepaths/root")),
			new SpDefault(new WtDefault(strWriter))
			
		);
		
		responsed.respond("/media/pics");
		assertEquals(expected, strWriter.toString());
	}

}
