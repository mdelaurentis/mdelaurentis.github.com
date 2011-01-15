---
layout: default
title: Mosaicing
---

Mosaicing
=========


Last term I took a class in Computer Vision at Drexel.  It was a really nice mixture of theory and programming assignments.  The second project was focused around creating a mosaic of a scene based on multiple images.  

Taking pictures
------------

The first step was to go out and take a bunch of pictures.  I tend to enjoy landscape photos, so I decided to go out to Valley Forge on a nice Saturday morning.  I parked on Yellow Springs Road, just west of where the covered bridge crosses over a creek next to 252.  There's a trail that starts there, goes up a big hill through the [woods](http://www.flickr.com/photos/mdelaurentis/sets/72157625625222491/), then back down past an old [stone building](http://www.flickr.com/photos/mdelaurentis/sets/72157625750745898/), and then follows a [creek](http://www.flickr.com/photos/mdelaurentis/sets/72157625750837878/) back to the woods.  I stopped and took pictures at a few spots on this trail:


<a href="http://www.flickr.com/photos/36641108@N05/5256795130/sizes/o" title="woods by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5207/5256795130_5543208792_z.jpg" width="640" height="71" alt="woods" /></a>

<a href="http://www.flickr.com/photos/36641108@N05/5256795246/sizes/o" title="trail by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5003/5256795246_3b9448b3cf_z.jpg" width="640" height="73" alt="trail" /></a>

<a href="http://www.flickr.com/photos/36641108@N05/5256795330/sizes/o" title="creek by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5282/5256795330_852f6bd002_z.jpg" width="640" height="73" alt="creek" /></a>

Then we went north on 252 a bit and made a right into the area where people fly model airplanes.  I took one set of [images](http://www.flickr.com/photos/mdelaurentis/sets/72157625750909834/) here.  I didn't want my car to show up in the images, so I put the tripod on top of it.  

<a href="http://www.flickr.com/photos/36641108@N05/5256795394/sizes/o" title="field by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5124/5256795394_0dcb4d1def_z.jpg" width="640" height="79" alt="field" /></a>

The following weekend, Lucy and I went back out to the boat club near Frosty Falls in Bridgeport.  There's a nice view of the Schuylkill river there, and the leaves were looking pretty nice.  This is my favorite of the panoramas.

<a href="http://www.flickr.com/photos/36641108@N05/5256795454/sizes/o" title="river by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5001/5256795454_c66f7c8d13_z.jpg" width="640" height="73" alt="river" /></a>

Then I went back to the house and spent the better part of the next week or two writing the code for the project.

<a href="http://www.flickr.com/photos/36641108@N05/5256795510/sizes/o" title="kitchen by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5127/5256795510_4f173a564e_z.jpg" width="640" height="70" alt="kitchen" /></a>


I put the camera on a tripod, pointed it at the brightest part of the scene, and locked in the aperture.  This prevents the camera from automatically adjusting the amount of light it lets in.  If I didn't do this step, the camera might automatically use different aperture settings for different pictures, which would result in visible differences in lighting across the panorama.  Then I take one picture, turn the camera a little bit to the right, take another picture, and repeat until I'm back where I started from.  With my camera, it took about twelve to fourteen pictures to capture the whole 360&deg; scene.

Cylindrical reprojection
-------------------

Once the pictures were taken, the next step is to warp each picture so that the edges overlap. You might think you could simply slide the images around until they overlap, but it's not quite that easy.  This required an understanding of how a real-world scene maps into an image.  Suppose you have a camera pointed at someone's face.  Imagine that each point on the person's face is connected to the center of the camera's lens with an invisible line.  So now you have this cone of invisible lines starting at the lens and extending out towards the subject's face.  Now if you were to drop a 3x5 index card through that cone a few inches in front of the lens, each of those invisible lines would intersect that card's plane at some point.  So you can figure out where a point in the scene will show up in the image by drawing this imaginary line from the scene, through the card, into the lens.  The spot where the line crosses the card is the spot where the point will render on the image.

<a href="http://www.flickr.com/photos/mdelaurentis/5327949603/" title="planar image by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5122/5327949603_43dbef2e02_o.png" width="360" height="288" alt="planar image" /></a>

Now the goal of this project is to take the images obtained by swiveling the camera around and stitch them together into a panorama.  So instead of an image plane as in the above example, we want an image cylinder.  We need to create a single image that's very short and quite wide.  When we swivel the camera around on the tripod and take a sequence of overlapping images, we get something like this:

<a href="http://www.flickr.com/photos/mdelaurentis/5327949645/" title="multiple planar images by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5044/5327949645_fcf90874d1_o.png" width="324" height="198" alt="multiple planar images" /></a>

Notice that this doesn't form a circle, but a weird shape (dodecagon?).  If we want the cylindrical panorama to be nice and smooth, we need to warp each of the original images and re-project each one onto the image cylinder, so that we end up with something more like this:

<a href="http://www.flickr.com/photos/mdelaurentis/5327949659/" title="cylindrical image by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5047/5327949659_4fe14aab4a_o.png" width="288" height="187" alt="cylindrical image" /></a>

This basically involves taking each point in each original image and calculating a corresponding point on the imaginary cylindar.  In order to do that you need to know the focal length of the camera (basically the distance between the center of projectiona and the magic "index card" we dropped in front of the lens).  In order to do that, I used [this tool](http://www.vision.caltech.edu/bouguetj/calib_doc/). Here's an example of what one of the images looks like before and after warping:

<a href="http://www.flickr.com/photos/mdelaurentis/5325535459/" title="IMG_1744 by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5242/5325535459_25e7c706dc_m.jpg" width="240" height="180" alt="IMG_1744" /></a>

<a href="http://www.flickr.com/photos/mdelaurentis/5329155334/" title="river-warped-5 by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5084/5329155334_b6289c5659_m.jpg" width="240" height="180" alt="river-warped-5" /></a>


Calculating optical flow
----------

Once all the images are warped around the imaginary image cylinder, the next step is to compute a translation for each image so that it lines up with its left and right neighbors.  To do this, we used the iterative [Lucas-Kanade method](http://en.wikipedia.org/wiki/Lucas–Kanade_Optical_Flow_Method) for calculating optical flow.

<a href="http://www.flickr.com/photos/mdelaurentis/5329186732/" title="river-overlay-4-5 by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5001/5329186732_52762e7967.jpg" width="500" height="257" alt="river-overlay-4-5" /></a>

Creating the panoramas
-------------------

Gradient-domain stitching
---------------------