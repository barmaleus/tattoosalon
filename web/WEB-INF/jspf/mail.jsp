<section>
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="sendmail" />
        <table>
            <tr>
                <td>Subject:</td>
                <td><input type="text" onkeyup="validate_add_publication(this)" name="subject"/></td>
            </tr>
        </table>
        <hr>
        <textarea type="text" onkeyup="validate_add_publication(this)" name="body" rows="5" cols="45">Message text</textarea>
        <br><br>
        <input type="submit" value="Send message!"/>
        <br>
        ${badProperties}
    </form>
</section>

