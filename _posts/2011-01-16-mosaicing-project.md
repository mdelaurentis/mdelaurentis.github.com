---
layout: post
title: Computer Vision Project - Mosaicing
published: false
---

Mosaicing
=========

Last term I took a class in Computer Vision at Drexel.  It was a really good class with a lot of theory and some interesting programming projects.  One of the projects involved taking a bunch of pictures and writing code in [Processing](http://processing.org) to stitch them together into a 360&deg; panorama.  I've used some open source tools (e.g. [Hugin](http://hugin.sourceforge.net/)) to create panoramas before, and it was interesting to learn about some of the concepts that a tool like that would use.  Anyway, here are the results of the mosaicing project, along with a brief description of the steps involved in making the panoramas.  I should mention that this strategy for creating the mosaics was clearly outlined by the professor, and we were given skeleton code that we filled in with implementations of the algorithms.  So the overall strategy was certainly not my idea, but the images are my own.

Taking pictures
---------------

The first step was to take a few sets of pictures at different locations.  The first Saturday after the project was assigned was a nice autumn day, so I took my dog Lucy out to Valley Forge and took some pictures there. I parked on Yellow Springs Road, just west of where the covered bridge crosses over a creek next to 252.  There's a trail that starts there, goes up a big hill through the [woods](http://www.flickr.com/photos/mdelaurentis/sets/72157625625222491/), then back down past an old [stone building](http://www.flickr.com/photos/mdelaurentis/sets/72157625750745898/), and then follows a [creek](http://www.flickr.com/photos/mdelaurentis/sets/72157625750837878/) back to the woods.  I stopped and took pictures at a few spots on this trail:

<p>
<div style="text-align: left">
<a href="http://www.flickr.com/photos/mdelaurentis/5325972132"><img src="http://farm6.static.flickr.com/5208/5325972132_4bb4a8fdd3_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325972358"><img src="http://farm6.static.flickr.com/5162/5325972358_8f9f0c3e80_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325365947"><img src="http://farm6.static.flickr.com/5166/5325365947_d793cb9f0b_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325366251"><img src="http://farm6.static.flickr.com/5204/5325366251_b33b533cba_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325973270"><img src="http://farm6.static.flickr.com/5050/5325973270_7a0738dd3e_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325366761"><img src="http://farm6.static.flickr.com/5245/5325366761_cfdf8aa0ef_t.jpg" style="padding-left: 0.5em;" /></a>
</div>

<div style="text-align: left">
<a href="http://www.flickr.com/photos/mdelaurentis/5325973726"><img src="http://farm6.static.flickr.com/5081/5325973726_b6d92354c2_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325973966"><img src="http://farm6.static.flickr.com/5002/5325973966_4be0fe2724_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325367475"><img src="http://farm6.static.flickr.com/5049/5325367475_1a90462684_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325974780"><img src="http://farm6.static.flickr.com/5129/5325974780_f42be95447_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325975150"><img src="http://farm6.static.flickr.com/5201/5325975150_af3d333276_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325368697"><img src="http://farm6.static.flickr.com/5241/5325368697_de7aa0616e_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325369029"><img src="http://farm6.static.flickr.com/5043/5325369029_4298035464_t.jpg" style="padding-left: 0.5em;" /></a>
</div>
</p>

<p>
<div style="text-align: left">
<a href="http://www.flickr.com/photos/mdelaurentis/5325387787"><img src="http://farm6.static.flickr.com/5087/5325387787_fc924b2161_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325388095"><img src="http://farm6.static.flickr.com/5282/5325388095_8549da74fc_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325995168"><img src="http://farm6.static.flickr.com/5288/5325995168_48422cc3c7_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325388613"><img src="http://farm6.static.flickr.com/5010/5325388613_370d32d719_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325995722"><img src="http://farm6.static.flickr.com/5244/5325995722_c6d07ca130_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325389191"><img src="http://farm6.static.flickr.com/5163/5325389191_8ca6c05890_t.jpg" style="padding-left: 0.5em;" /></a>
</div>

<div style="text-align: left">
<a href="http://www.flickr.com/photos/mdelaurentis/5325389507"><img src="http://farm6.static.flickr.com/5243/5325389507_e8e9823b00_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325389745"><img src="http://farm6.static.flickr.com/5088/5325389745_2cfac22ea4_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325390165"><img src="http://farm6.static.flickr.com/5090/5325390165_91a08ae3f8_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325997470"><img src="http://farm6.static.flickr.com/5086/5325997470_fe8e49814b_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325997764"><img src="http://farm6.static.flickr.com/5168/5325997764_498a27c572_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325391005"><img src="http://farm6.static.flickr.com/5045/5325391005_4fc8fc95eb_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325391407"><img src="http://farm6.static.flickr.com/5007/5325391407_d70a0e18b3_t.jpg" style="padding-left: 0.5em;" /></a>

</div>
</p>


<p>
<div style="text-align: left">
<a href="http://www.flickr.com/photos/mdelaurentis/5325435027"><img src="http://farm6.static.flickr.com/5241/5325435027_ab885f8a03_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326042648"><img src="http://farm6.static.flickr.com/5049/5326042648_887c9a63ba_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326042916"><img src="http://farm6.static.flickr.com/5083/5326042916_8d2a157887_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326043210"><img src="http://farm6.static.flickr.com/5204/5326043210_12d1ac3daa_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326043466"><img src="http://farm6.static.flickr.com/5090/5326043466_653b38cfb1_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325436415"><img src="http://farm6.static.flickr.com/5241/5325436415_27758ba4d4_t.jpg" style="padding-left: 0.5em;" /></a>
</div>

<div style="text-align: left">
<a href="http://www.flickr.com/photos/mdelaurentis/5325436673"><img src="http://farm6.static.flickr.com/5283/5325436673_9be960d1dd_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326044278"><img src="http://farm6.static.flickr.com/5050/5326044278_2676d059d5_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326044578"><img src="http://farm6.static.flickr.com/5290/5326044578_aa4977b498_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326044846"><img src="http://farm6.static.flickr.com/5246/5326044846_b06de9d7b3_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326045112"><img src="http://farm6.static.flickr.com/5247/5326045112_7e6e298faa_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326045342"><img src="http://farm6.static.flickr.com/5242/5326045342_303a300115_t.jpg" style="padding-left: 0.5em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325438373"><img src="http://farm6.static.flickr.com/5208/5325438373_c513cf11d9_t.jpg" style="padding-left: 0.5em;" /></a>
</div>
</p>

Then we went north on 252 a bit and made a right into the area where people fly model airplanes.  I took one set of [images](http://www.flickr.com/photos/mdelaurentis/sets/72157625750909834/) here.  I didn't want my car to show up in the images, so I put the tripod on top of it.  

<figure>
<div style="text-align: left">
<a href="http://www.flickr.com/photos/mdelaurentis/5325469983"><img src="http://farm6.static.flickr.com/5129/5325469983_a4d7794bce_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325470173"><img src="http://farm6.static.flickr.com/5047/5325470173_2294331b5f_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325470655"><img src="http://farm6.static.flickr.com/5284/5325470655_76b63ac808_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325470863"><img src="http://farm6.static.flickr.com/5170/5325470863_3261e2d73c_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325471103"><img src="http://farm6.static.flickr.com/5202/5325471103_8459a663f7_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325471327"><img src="http://farm6.static.flickr.com/5206/5325471327_4b875bb6ab_t.jpg" style="padding-left: 1em;" /></a>
</div>
<div style="text-align: left">
<a href="http://www.flickr.com/photos/mdelaurentis/5325471603"><img src="http://farm6.static.flickr.com/5125/5325471603_f92eb34426_t.jpg" style="padding-left: 1em;" /></a>

<a href="http://www.flickr.com/photos/mdelaurentis/5326078996"><img src="http://farm6.static.flickr.com/5010/5326078996_a2a84a4509_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326079250"><img src="http://farm6.static.flickr.com/5210/5326079250_23fa0c68ff_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326079458"><img src="http://farm6.static.flickr.com/5163/5326079458_444746fb7e_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325472569"><img src="http://farm6.static.flickr.com/5010/5325472569_e7323447c9_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325472831"><img src="http://farm6.static.flickr.com/5083/5325472831_df02f427a4_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326080178"><img src="http://farm6.static.flickr.com/5130/5326080178_81a7e8d3f2_t.jpg" style="padding-left: 1em;" /></a>
</div>
</figure>

The following weekend, Lucy and I went back out to the boat club near Frosty Falls in Bridgeport. There's a nice view of the Schuylkill river there, and the leaves were looking pretty nice.

<figure>
<div style="text-align: left">
<a href="http://www.flickr.com/photos/mdelaurentis/5326140672"><img src="http://farm6.static.flickr.com/5285/5326140672_b3d8b2d061_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326140898"><img src="http://farm6.static.flickr.com/5122/5326140898_ea3a73ce62_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325533963"><img src="http://farm6.static.flickr.com/5041/5325533963_d2976531fb_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326141570"><img src="http://farm6.static.flickr.com/5088/5326141570_54d21d26e5_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326141788"><img src="http://farm6.static.flickr.com/5086/5326141788_a3ce5ddec8_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325535007"><img src="http://farm6.static.flickr.com/5125/5325535007_5e9eaa2ca1_t.jpg" style="padding-left: 1em;" /></a>
</div>
<div style="text-align: left">
<a href="http://www.flickr.com/photos/mdelaurentis/5326142428"><img src="http://farm6.static.flickr.com/5285/5326142428_2638daf131_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325535459"><img src="http://farm6.static.flickr.com/5242/5325535459_5e0f5d0e87_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326142948"><img src="http://farm6.static.flickr.com/5049/5326142948_4919083102_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325535987"><img src="http://farm6.static.flickr.com/5041/5325535987_a6c9e2d652_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5325536263"><img src="http://farm6.static.flickr.com/5003/5325536263_5e7fb2ae6f_t.jpg" style="padding-left: 1em;" /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5326143766"><img src="http://farm6.static.flickr.com/5090/5326143766_09f1d1a582_t.jpg" style="padding-left: 1em;" /></a>
</div>
</figure>

At each location, I put the camera on a tripod, pointed it at the brightest part of the scene, and locked in the aperture.  This prevents the camera from automatically adjusting the amount of light it lets in.  If I didn't do this step, the camera might automatically use different aperture settings for different pictures, which would result in visible differences in lighting across the panorama.  Then I took one picture, turned the camera a little bit to the right, took another picture, and repeated until the camera was back where it started.  With my camera, it took about twelve to fourteen pictures to capture a whole 360&deg; scene.

Then I went back to the house and spent the better part of the next week or two writing the code for the project.



Cylindrical reprojection
-------------------

Once the pictures were taken, the next step is to warp each picture so we can line it up with its neighbors. It might seem like you could simply slide the images around until they overlap, but it's not quite that easy, because of the way the camera produces the image.  Suppose you have a camera pointed at someone's face.  Imagine that each point on the person's face is connected to the center of the camera's lens with an invisible line.  So now you have this cone of invisible lines starting at the lens and extending out towards the subject's face.  Now if you were to drop a 3x5 index card through that cone a few inches in front of the lens, each of those invisible lines would intersect that card's plane at some point.  So you can figure out where a point in the scene will show up in the image by drawing this imaginary line from the scene, through the card, into the lens.  The spot where the line crosses the card is the spot where the point will render on the image.

<a href="http://www.flickr.com/photos/mdelaurentis/5327949603/" title="planar image by mdelaurentis, on Flickr"><img class="figure" src="http://farm6.static.flickr.com/5122/5327949603_43dbef2e02_o.png" width="360" height="288" alt="planar image" /></a>

Now the goal of this project is to take the images obtained by swiveling the camera around and stitch them together into a panorama.  So instead of an image plane as in the above example, we want a wide image that wraps all the way around the camera; something more like an image cylinder:

<a href="http://www.flickr.com/photos/mdelaurentis/5327949659/" title="cylindrical image by mdelaurentis, on Flickr"><img class="figure" src="http://farm6.static.flickr.com/5047/5327949659_4fe14aab4a_o.png" width="288" height="187" alt="cylindrical image" /></a>

However the images we obtained by swiveling the camera around on the tripod are more like this this:

<a href="http://www.flickr.com/photos/mdelaurentis/5327949645/" title="multiple planar images by mdelaurentis, on Flickr"><img class="figure" src="http://farm6.static.flickr.com/5044/5327949645_fcf90874d1_o.png" width="324" height="198" alt="multiple planar images" /></a>

Notice that this doesn't form a circle, but a weird shape (dodecagon?).  If we want the cylindrical panorama to be nice and smooth, we need to warp each of the original images and re-project each one onto an imaginary image cylindar.  This basically involves taking each point in each original image and calculating a corresponding point on the imaginary cylindar.  In order to do that you need to know the focal length of the camera (basically the distance between the center of projectiona and the magic "index card" we dropped in front of the lens).  I used [this tool](http://www.vision.caltech.edu/bouguetj/calib_doc/) to calibrate the camera. Here's an example of what one of the images looks like before and after warping:

<div style="text-align: center">
<a href="http://www.flickr.com/photos/mdelaurentis/5325535459/" title="IMG_1744 by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5242/5325535459_5e0f5d0e87_m.jpg" width="240" height="180" alt="IMG_1744" /></a>

<a href="http://www.flickr.com/photos/mdelaurentis/5329155334/" title="river-warped-5 by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5084/5329155334_b6289c5659_m.jpg" width="240" height="180" alt="river-warped-5" /></a>
</div>


Calculating optical flow
----------

Once all the images are warped around the imaginary image cylinder, the next step is to compute a translation for each image so that it lines up with its left and right neighbors.  To do this, we used the iterative [Lucas-Kanade method](http://en.wikipedia.org/wiki/Lucas-Kanade_Optical_Flow_Method) for calculating optical flow.  This method basically takes two images and finds finds an optimal translation of one image to make it line up with the other image.  It assumes that the motion between the two images is less than one pixel.  In order to deal with this assumption, we need to reduce the resolution of the images to the point where the motion across the two images actually is less than one pixel.  Then we calculate the optical flow between these two tiny images, then scale the images back up a bit, apply the translation we just computed, and repeat until we're back at the original resolution.

Here's an example of two of the warped images lined up according to the translation given by the Lucas-Kanade method.  You can see the warped edges of the images here, and it's evident that the warping helps the images line up properly.

<div style="text-align: center">
<a href="http://www.flickr.com/photos/mdelaurentis/5329186732/" title="river-overlay-4-5 by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5001/5329186732_52762e7967.jpg" width="500" height="257" alt="river-overlay-4-5" /></a>
</div>

Creating the panoramas
-------------------

Once I produced the cylindrical projection of each image and found a translation for each of the adjacent images, the next step is to arrange all the warped images together into a final panorama.  First I created an image that consisted of all the source images together, with the appropriate translations applied.  I used simple linear feathering in the overlapping regions of the images to try to reduce the visibility of the seams.  That process produced an image like this:

<a href="http://www.flickr.com/photos/mdelaurentis/5357425746/" title="woods-uncropped by mdelaurentis, on Flickr"><img src="http://farm6.static.flickr.com/5249/5357425746_e189deb2e2_b.jpg" width="940" alt="woods-uncropped" /></a>

Notice that the images to the left are significantly lower than the ones to the right.  This was probably caused by one leg of the tripod sinking into the ground a bit while I was taking the photos.  To fix this, I used the translation given by Lucas-Kanade for the left-most and right-most images to skew the image so that it appears horizontal, and then cropped off the outer rows and columns of black pixels.  Here were the results:

<a href="http://www.flickr.com/photos/mdelaurentis/5256795130"><img width="940" src="http://farm6.static.flickr.com/5207/5256795130_5543208792_b.jpg"  /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5256795246"><img width="940" src="http://farm6.static.flickr.com/5003/5256795246_3b9448b3cf_b.jpg"  /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5256795330"><img width="940" src="http://farm6.static.flickr.com/5282/5256795330_852f6bd002_b.jpg"  /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5256795394"><img width="940" src="http://farm6.static.flickr.com/5124/5256795394_0dcb4d1def_b.jpg"  /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5256795454"><img width="940" src="http://farm6.static.flickr.com/5001/5256795454_c66f7c8d13_b.jpg"  /></a>
<a href="http://www.flickr.com/photos/mdelaurentis/5256795510"><img width="940" src="http://farm6.static.flickr.com/5127/5256795510_4f173a564e_b.jpg"  /></a>

