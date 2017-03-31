
import java.io.IOException;

import ij.ImagePlus;
import ij.ImageStack;
import ij.process.ImageProcessor;
import loci.formats.FormatException;
import loci.formats.in.NiftiReader;
import loci.plugins.BF;
import loci.plugins.util.ImageProcessorReader;

public class OpenNifti
{

	public static void main( String[] args ) throws FormatException, IOException
	{

		// This ImagePlus is the correct size, but contains the wrong data
		ImagePlus ip = BF.openImagePlus( args[ 0 ] )[ 0 ];
		System.out.println( "timepts " + ip.getNFrames() );
		System.out.println( "depth " + ip.getNSlices() );
		System.out.println( "channels " + ip.getNChannels() );
		ip.setC( 0 );
		ip.setZ( 60 );
		System.out.println( ip.getProcessor().getf( 120, 120 ) );

		// The code below produces an ImagePlus with the correct size and data
		NiftiReader reader = new NiftiReader();
		reader.setId( args[ 0 ] );

		int width = reader.getSizeX();
		int height = reader.getSizeY();
		int depth = reader.getSizeZ();
		int timepts = reader.getSizeT();
		int channels = reader.getSizeC();
		int imgCount = reader.getImageCount();
		System.out.println( "img count" + imgCount );
		System.out.println( "timepts " + timepts );
		System.out.println( "depth " + depth );
		System.out.println( "channels " + channels );

		ImageProcessorReader ipr = new ImageProcessorReader( reader );
		ImageStack stack = new ImageStack( width, height );
		for ( int i = 0; i < imgCount; i++ )
		{
			ImageProcessor[] processors = ipr.openProcessors( i );
			stack.addSlice( processors[ 0 ] );
		}
		reader.close();
		ipr.close();

		ImagePlus anotherIp = new ImagePlus( args[ 0 ], stack );
		anotherIp.setDimensions( 1, depth, channels );
		anotherIp.setC( 0 );
		anotherIp.setZ( 60 );
		System.out.println( anotherIp.getProcessor().getf( 120, 120 ) );
	}

}
