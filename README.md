WrodCloud java/android versoin
===========================
##java versoin
###use:
<li>create new gui_loop</li>
<li>input JFrame and ArrayList\<StringInfo\></li>

example:

        gui_loop guiloop = new gui_loop(frame, words);

<li>then call `gui_loop.init_dot_set()` and `gui_loop.run()`</li>
<li>finally, output the WordCloud picture</li>

----

##Android versoin

###amost finish
###How to use?
<li>create a new WordCloud Object (must input some data and Object)
    <li>1.WordInfos (have word, size, padding)</li>
    <li>2.ImageView (width and height)</li>
    <li>3.button</li>
    <li>4.now Activity</li>
</li>

example:

        WordCloud wc = new WordCloud(Words, MaxWidth, MaxHeight, Image, Button, Activity);

<li>call WordCloud.init_dot_set() function</li>
<li>call WordCloud.start() function</li>
