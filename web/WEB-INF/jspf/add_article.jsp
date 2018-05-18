<section>
    <h2>Creating article</h2>
    <p>Fulfill this form to upload new article to the site.</p>

    <form action="controller" method="POST">
        <input type="hidden" name="command" value="confirmation" />
        <fieldset>
            <legend>Creating form:</legend>
            <span class="req">*</span>Title:
            <br>
            <input required type="text" name="title" placeholder="Write title here"><br>
            <span class="req">*</span>Text:
            <br>
            <textarea required name="text" style="width:700px; height:600px;" placeholder="Write your text here"></textarea>
            <br>
            <input type="submit" value="Upload text" />
        </fieldset>
    </form>
</section>
