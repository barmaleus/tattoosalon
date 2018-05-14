package by.rekuts.tattoosalon.command;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    TO_REGISTRATION_PAGE {
        {
            this.command = new ToRegistrationPageCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    SENDMAIL {
        {
            this.command = new SendMailCommand();
        }
    },
    BLOCK_USER {
        {
            this.command = new BlockUserCommand();
        }
    },
    CABINET_BLOCK_USER {
        {
            this.command = new CabinetBlockCommand();
        }
    },
    MAIN {
        {
            this.command = new PageMainCommand();
        }
    },
    ARTICLE {
        {
            this.command = new PageArticleCommand();
        }
    },
    GALLERY {
        {
            this.command = new PageGalleryCommand();
        }
    },
    MASTERS {
        {
            this.command = new PageMastersCommand();
        }
    },
    MAIL {
        {
            this.command = new PageMailCommand();
        }
    },
    CABINET {
        {
            this.command = new PageCabinetCommand();
        }
    },
    PUBLICATION {
        {
            this.command = new PagePublicationCommand();
        }
    },
    ADMIN {
        {
            this.command = new PageAdminCommand();
        }
    },
    ADD_PORTFOLIO {
        {
            this.command = new AddPortfolioCommand();
        }
    },
    ADD_ARTICLE {
        {
            this.command = new AddArticleCommand();
        }
    },
    UPLOADED {
        {
            this.command = new PageUploadedCommand();
        }
    },
    BLOCK_PUBLICATION {
        {
            this.command = new BlockPublicationCommand();
        }
    },
    ROLE_USER {
        {
            this.command = new ChangeUserRoleCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
