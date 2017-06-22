Get-ChildItem "config" -Filter *.yml | 
Foreach-Object {
   $name = $_.Basename
   
   curl -Method PUT -InFile $_.FullName http://localhost:8500/v1/kv/config/$name/data
}