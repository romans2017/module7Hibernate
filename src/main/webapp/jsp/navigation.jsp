<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Main page</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/companies">Companies</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/customers">Customers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/projects">Projects</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/developers">Developers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/skills">Skills</a>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Reports
                  </a>
                  <ul class="dropdown-menu" aria-labelledby="navbarDarkDropdownMenuLink">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportDevelopers/salary">Report "Developers' salary by project"</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportDevelopers/project">Report "Developers by project"</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportDevelopers/java">Report "Java developers"</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportDevelopers/middle">Report "Middle-level developers"</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportProjects">Report "Projects"</a></li>
                  </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>