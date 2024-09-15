# git@github.com: Permission denied (publickey).

```text 
git@github.com: Permission denied (publickey).
fatal: Could not read from remote repository.
```

1. Open ternimal

2. Enter `ls -al ~/.ssh` to see if existing SSH keys are present.

  - id_rsa.pub
  - id_ecdsa.pub
  - id_ed25519.pub

  ```
  $ cat id_rsa.pub
  {title} {keys} {user_id} // copy
  ```

  - Login Github > Settings > SSH and GPG keys > `new SSH Key` > submit

3. Either generate a new SSH key or upload an existing key.

  - Read to `02-ssh-auth.md`

----

- [Links](https://docs.github.com/ko/authentication/troubleshooting-ssh/error-permission-denied-publickey)
