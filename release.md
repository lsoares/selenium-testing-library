brew install gpg
gpg --gen-key
gpg -K
gpg --keyserver keyserver.ubuntu.com --send-keys <PUBLIC_KEY>
gpg --keyring secring.gpg --export-secret-keys > ~/.gnupg/secring.gpg



- CREATE GIT IGNORED gradle.properties FILE WITH:
ossrhUsername=
ossrhPassword=
signing.keyId=last 8 chars of public key id
signing.password=
signing.secretKeyRingFile=/Users/lsoares/.gnupg/secring.gpg