<section>
    <h2>${requestScope.message}</h2>
    <br>
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="main" />
        <input type="hidden" name="page" value="0" />
        <input type="submit" value="Back to main page" />
    </form>
</section>